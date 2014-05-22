/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.europeana.corelib.solr.utils.construct;

import eu.europeana.corelib.definitions.model.EdmLabel;
import eu.europeana.corelib.definitions.solr.entity.EuropeanaAggregation;
import eu.europeana.corelib.definitions.solr.entity.WebResource;
import eu.europeana.corelib.solr.utils.SolrUtils;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author Yorgos.Mamakis@ europeana.eu
 */
public class EuropeanaAggregationSolrCreator {

    public void create(SolrInputDocument doc, EuropeanaAggregation aggr) {
        SolrUtils.addFromString(doc, EdmLabel.EDM_EUROPEANA_AGGREGATION, aggr.getAbout());
        SolrUtils.addFromMap(doc, EdmLabel.EUROPEANA_AGGREGATION_DC_CREATOR, aggr.getDcCreator());
        SolrUtils.addFromMap(doc, EdmLabel.EUROPEANA_AGGREGATION_EDM_RIGHTS, aggr.getEdmRights());
        SolrUtils.addFromMap(doc, EdmLabel.EUROPEANA_AGGREGATION_EDM_COUNTRY, aggr.getEdmCountry());
        SolrUtils.addFromMap(doc, EdmLabel.EUROPEANA_AGGREGATION_EDM_LANGUAGE, aggr.getEdmLanguage());
        SolrUtils.addFromStringArray(doc, EdmLabel.EUROPEANA_AGGREGATION_ORE_AGGREGATES, aggr.getAggregates());
        SolrUtils.addFromStringArray(doc, EdmLabel.EUROPEANA_AGGREGATION_EDM_HASVIEW, aggr.getEdmHasView());
        SolrUtils.addFromString(doc, EdmLabel.EUROPEANA_AGGREGATION_ORE_AGGREGATEDCHO, aggr.getAggregatedCHO());
        SolrUtils.addFromString(doc, EdmLabel.EUROPEANA_AGGREGATION_EDM_LANDINGPAGE, aggr.getEdmLandingPage());
        SolrUtils.addFromString(doc, EdmLabel.EUROPEANA_AGGREGATION_EDM_ISSHOWNBY, aggr.getEdmIsShownBy());
        SolrUtils.addFromString(doc, EdmLabel.EUROPEANA_AGGREGATION_EDM_PREVIEW, aggr.getEdmPreview());
        if (aggr.getWebResources() != null) {
            for (WebResource wr : aggr.getWebResources()) {
                new WebResourceSolrCreator().create(doc, wr);
            }
        }
    }
}