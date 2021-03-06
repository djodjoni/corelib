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
package eu.europeana.corelib.definitions.solr.entity;

import java.util.List;
import java.util.Map;

/**
 * EDM Place fields representation
 * 
 * @author Yorgos.Mamakis@ kb.nl
 * 
 */
public interface Place extends ContextualClass {

	/**
	 * Retrieves the dcterms:isPartOf fields for a Place
	 * 
	 * @return A String array representing the dcterms:isPartOf fields for a
	 *         Place
	 */
	Map<String,List<String>> getIsPartOf();

	/**
	 * Retrieves the latitude of a Place
	 * 
	 * @return A float representing the latitude of a Place
	 */
	Float getLatitude();

	/**
	 * Retrieves the longitude of a Place
	 * 
	 * @return A float representing the longitude of a Place
	 */
	Float getLongitude();

	/**
	 * Set the dcterms:isPartOf fields for a Place
	 * 
	 * @param isPartOf
	 *            A String array representing the dcterms:isPartOf fields for a
	 *            Place
	 */
	void setIsPartOf(Map<String,List<String>> isPartOf);

	/**
	 * Set the latitude for a place
	 * 
	 * @param latitude
	 *            A float representing the latitude of a Place
	 */
	void setLatitude(Float latitude);

	/**
	 * Set the longitude for a place
	 * 
	 * @param longitude
	 *            A float representing the longitude of a Place
	 */
	void setLongitude(Float longitude);

	/**
	 * set the altitude for a place
	 * @param altitude
	 */
	void setAltitude(Float altitude);
	
	/**
	 * 
	 * @return the altitude for a place
	 */
	Float getAltitude();
	
	/**
	 * sets the position for a place
	 * @param position
	 */
	void setPosition(Map<String,Float> position);
	
	/**
	 * 
	 * @return the position for a place
	 */
	Map<String,Float> getPosition();
	
	/**
	 * sets the dcterms:hasPart for a Place
	 * @param dcTermsHasPart 
	 */
	void setDcTermsHasPart(Map<String,List<String>> dcTermsHasPart);
	
	/**
	 * 
	 * @return the dcterms:hasPart for a place
	 */
	Map<String,List<String>> getDcTermsHasPart();
	
	/**
	 * sets the owl:sameAs for a place
	 * @param owlSameAs
	 */
	void setOwlSameAs(String[] owlSameAs);
	
	/**
	 * 
	 * @return the owl:sameAs for a place
	 */
	String[] getOwlSameAs();
}
