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
package eu.europeana.corelib.solr.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.io.FileUtils;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.junit.Test;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import eu.europeana.corelib.definitions.jibx.EuropeanaProxy;
import eu.europeana.corelib.definitions.jibx.ProxyType;
import eu.europeana.corelib.definitions.jibx.RDF;
import eu.europeana.corelib.definitions.solr.beans.FullBean;
import eu.europeana.corelib.solr.exceptions.MongoDBException;
import eu.europeana.corelib.solr.server.EdmMongoServer;
import eu.europeana.corelib.solr.server.impl.EdmMongoServerImpl;

/**
 * Mongo Constructor unit tests
 * 
 * @author Yorgos.Mamakis@ kb.nl
 * 
 */
public class MongoConstructorTest {

	@Test
	public void test() {
		try {
			IBindingFactory bfact = BindingDirectory.getFactory(RDF.class);
			IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
			RDF rdf = (RDF) uctx.unmarshalDocument(new StringReader(
					FileUtils.readFileToString(new File(
							"src/test/resources/test_files/edm_new.xml"))));
			int port = 10000;
			MongodConfig conf = new MongodConfig(Version.V2_0_7, port, false);

			MongodStarter runtime = MongodStarter.getDefaultInstance();

			MongodExecutable mongodExecutable = runtime.prepare(conf);
			mongodExecutable.start();
			EdmMongoServer mongoServer = new EdmMongoServerImpl(new Mongo(
					"localhost", port), "europeana_test", "", "");
			FullBean fBean = new MongoConstructor().constructFullBean(rdf,
					mongoServer);
			assertNotNull(fBean);
			ProxyType proxy = rdf.getProxyList().get(0);
			EuropeanaProxy eProxy = new EuropeanaProxy();
			eProxy.setEuropeanaProxy(true);
			proxy.setEuropeanaProxy(eProxy);
			rdf.getProxyList().clear();
			rdf.getProxyList().add(proxy);
			FullBean fBeanUpdate = new MongoConstructor().constructFullBean(rdf, mongoServer);
			assertNotNull(fBeanUpdate);
			assertTrue(fBeanUpdate.getProxies().get(0).isEuropeanaProxy());
			
			mongodExecutable.stop();
		} catch (JiBXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MongoDBException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
