/*
 * Copyright 2007-2012 The Europeana Foundation
 *
 *  Licenced under the EUPL, Version 1.1 (the "Licence") and subsequent versions as approved
 *  by the European Commission;
 *  You may not use this work except in compliance with the Licence.
 * 
 *  You may obtain a copy of the Licence at:
 *  http://joinup.ec.europa.eu/software/page/eupl
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under
 *  the Licence is distributed on an "AS IS" basis, without warranties or conditions of
 *  any kind, either express or implied.
 *  See the Licence for the specific language governing permissions and limitations under
 *  the Licence.
 */
package eu.europeana.corelib.solr.test.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.common.SolrInputDocument;
import org.easymock.EasyMock;
import org.junit.Test;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.query.Query;

import eu.europeana.corelib.definitions.jibx.AgentType;
import eu.europeana.corelib.definitions.jibx.AltLabel;
import eu.europeana.corelib.definitions.jibx.Begin;
import eu.europeana.corelib.definitions.jibx.End;
import eu.europeana.corelib.definitions.jibx.LiteralType.Lang;
import eu.europeana.corelib.definitions.jibx.Note;
import eu.europeana.corelib.definitions.jibx.PrefLabel;
import eu.europeana.corelib.definitions.model.EdmLabel;
import eu.europeana.corelib.solr.entity.AgentImpl;
import eu.europeana.corelib.solr.server.EdmMongoServer;
import eu.europeana.corelib.solr.server.importer.util.AgentFieldInput;

/**
 * Unit test for the Agent field input creator
 * @author Yorgos.Mamakis@ kb.nl
 *
 */
public class AgentFieldInputTest {

	private EdmMongoServer mongoServer;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testAgent() throws MalformedURLException, IOException{
		AgentType agentType = new AgentType();
		agentType.setAbout("test about");
		mongoServer = EasyMock.createMock(EdmMongoServer.class);
		Datastore ds = EasyMock.createMock(Datastore.class);
		Query query = EasyMock.createMock(Query.class);
		Key<AgentImpl> key = EasyMock.createMock(Key.class);
		EasyMock.expect(mongoServer.getDatastore()).andReturn(ds);
		EasyMock.expect(ds.find(AgentImpl.class)).andReturn(query);
		EasyMock.expect(query.filter("about", agentType.getAbout())).andReturn(query);
		EasyMock.expect(query.get()).andReturn(null);
		EasyMock.expect(mongoServer.getDatastore()).andReturn(ds);
		AgentImpl agentImpl = new AgentImpl();
		agentImpl.setAbout(agentType.getAbout());
		EasyMock.expect(ds.save(agentImpl)).andReturn(key);
		
		EasyMock.replay(query,ds,mongoServer);
		assertNotNull(mongoServer);
	
		List<AltLabel> altLabelList = new ArrayList<AltLabel>();
		AltLabel altLabel = new AltLabel();
		Lang lang = new Lang();
		lang.setLang("en");
		altLabel.setLang(lang);
		altLabel.setString("test alt label");
		assertNotNull(altLabel);
		altLabelList.add(altLabel);
		agentType.setAltLabelList(altLabelList);
		Begin begin = new Begin();
		begin.setString("test begin");
		agentType.setBegin(begin);
		End end = new End();
		end.setString("test end");
		agentType.setEnd(end);
		List<Note> noteList = new ArrayList<Note>();
		Note note = new Note();
		note.setString("test note");
		assertNotNull(note);
		noteList.add(note);
		agentType.setNoteList(noteList);
		List<PrefLabel> prefLabelList = new ArrayList<PrefLabel>();
		PrefLabel prefLabel = new PrefLabel();
		prefLabel.setLang(lang);
		prefLabel.setString("test pred label");
		assertNotNull(prefLabel);
		prefLabelList.add(prefLabel);
		agentType.setPrefLabelList(prefLabelList);

		//store in mongo
		AgentImpl agent = new AgentFieldInput().createAgentMongoEntity(agentType,
				mongoServer);
		assertEquals(agentType.getAbout(), agent.getAbout());
		assertEquals(agentType.getBegin().getString(), agent.getBegin().values().iterator().next().get(0));
		assertEquals(agentType.getEnd().getString(), agent.getEnd().values().iterator().next().get(0));
		assertEquals(agentType.getNoteList().get(0).getString(),
				agent.getNote().values().iterator().next().get(0));
		assertTrue(agent.getAltLabel().containsKey(
				agentType.getAltLabelList().get(0).getLang().getLang()));
		assertTrue(agent.getPrefLabel().containsKey(
				agentType.getPrefLabelList().get(0).getLang().getLang()));
		assertEquals(agentType.getAltLabelList().get(0).getString(),
				agent.getAltLabel().values().iterator().next().get(0));
		assertEquals(agentType.getPrefLabelList().get(0).getString(),
						agent.getPrefLabel().values().iterator().next().get(0));

		//create solr document
		SolrInputDocument solrDocument = new SolrInputDocument();
		solrDocument = new AgentFieldInput().createAgentSolrFields(agentType,
				solrDocument);
		assertEquals(agentType.getAbout(),
				solrDocument.getFieldValue(EdmLabel.EDM_AGENT.toString())
						.toString());
		assertEquals(agentType.getBegin().getString(),
				solrDocument.getFieldValues(EdmLabel.AG_EDM_BEGIN.toString())
						.toArray()[0].toString());
		assertEquals(agentType.getEnd().getString(),
				solrDocument.getFieldValues(EdmLabel.AG_EDM_END.toString())
						.toArray()[0].toString());
		assertEquals(agentType.getNoteList().get(0).getString(),
				solrDocument.getFieldValues(EdmLabel.AG_SKOS_NOTE.toString())
						.toArray()[0].toString());
		assertEquals(
				agentType.getAltLabelList().get(0).getString(),
				solrDocument.getFieldValues(
						EdmLabel.AG_SKOS_ALT_LABEL.toString()
								+ "."
								+ agentType.getAltLabelList().get(0).getLang()
										.getLang()).toArray()[0].toString());

		assertEquals(
				agentType.getPrefLabelList().get(0).getString(),
				solrDocument.getFieldValues(
						EdmLabel.AG_SKOS_PREF_LABEL.toString()
								+ "."
								+ agentType.getAltLabelList().get(0).getLang()
										.getLang()).toArray()[0].toString());

	}

	
}
