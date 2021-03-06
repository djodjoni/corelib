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

package eu.europeana.corelib.definitions.solr.beans;

import java.util.Date;
import java.util.List;
import java.util.Map;

import eu.europeana.corelib.definitions.solr.DocType;

/**
 * Interface for the BriefBean. The BriefBean contains the fields exposed by the
 * SOLR engine for presenting each record in the result and search page.
 * 
 * 
 * @author Yorgos Mamakis <yorgos.mamakis@kb.nl>
 */
public interface BriefBean extends IdBean {

	/**
	 * 
	 * @return TITLE field
	 */
	String[] getTitle();

	/**
	 * 
	 * @return edm:object
	 */
	String[] getEdmObject();

	/**
	 * 
	 * @return YEAR field
	 */
	String[] getYear(); // YEAR copyfield dc_date

	/**
	 * 
	 * @return PROVIDER field
	 */
	String[] getProvider(); // PROVIDER copyfield edm_provider

	/**
	 * 
	 * @return DATAPROVIDER field
	 */
	String[] getDataProvider(); // DATA_PROVIDER copyfield edm_dataProvider

	/**
	 * 
	 * @return LANGUAGE field
	 */
	String[] getLanguage(); // LANGUAGE copyfield edm_language

	/**
	 * 
	 * @return RIGHTS field
	 */
	String[] getRights(); // LANGUAGE copyfield dc_language

	/**
	 * 
	 * @return TYPE field
	 */
	DocType getType(); // TYPE copyfield edm_type

	// here the dcterms namespaces starts

	/**
	 * 
	 * @return dcterms:spatial
	 */
	String[] getDctermsSpatial();

	/**
	 * 
	 * @return edm:isShownAt
	 */
	String[] getEdmIsShownAt();

	// Ranking and Enrichment terms

	/**
	 * 
	 * @return europeana_completeness field
	 */
	int getEuropeanaCompleteness();

	/**
	 * 
	 * @return edm:place
	 */
	String[] getEdmPlace();

	/**
	 * 
	 * @return edm:place skos:prefLabel multilingual field
	 */
	List<Map<String, String>> getEdmPlaceLabel();

	/**
	 * 
	 * @return edm:place wgs84:posLat
	 */
	List<String> getEdmPlaceLatitude();

	/**
	 * 
	 * @return edm:place wgs84:posLong
	 */
	List<String> getEdmPlaceLongitude();

	/**
	 * 
	 * @return edm:timespan
	 */
	String[] getEdmTimespan();

	/**
	 * 
	 * @return edm:timespan skos:prefLabel multilingual field
	 */
	List<Map<String, String>> getEdmTimespanLabel();

	/**
	 * 
	 * @return edm:timespan edm:begin
	 */
	String[] getEdmTimespanBegin();

	/**
	 * 
	 * @return edm:timespan edm:end
	 */
	String[] getEdmTimespanEnd();

	/**
	 * 
	 * @return edm:agent
	 */
	String[] getEdmAgent();

	/**
	 * 
	 * @return edm:agent skos:prefLabel multilingual field
	 */
	List<Map<String, String>> getEdmAgentLabel();

	/**
	 * 
	 * @return dcterms:hasPart
	 */
	String[] getDctermsHasPart();

	/**
	 * 
	 * @return dc:creator
	 */
	String[] getDcCreator();

	/**
	 * 
	 * @return dc:contributor
	 */
	String[] getDcContributor();

	/**
	 * 
	 * @return Time of indexing of the record
	 */
	Date getTimestamp();

	/**
	 * 
	 * @return edm:preview field
	 */
	String[] getEdmPreview();

	/**
	 * 
	 * @return the score of the result
	 */
	float getScore();
        
        Map<String, List<String>> getEdmPlaceLabelLangAware();
        
        Map<String, List<String>> getEdmTimespanLabelLangAware();
        
        Map<String, List<String>> getEdmAgentLabelLangAware();
                
}
