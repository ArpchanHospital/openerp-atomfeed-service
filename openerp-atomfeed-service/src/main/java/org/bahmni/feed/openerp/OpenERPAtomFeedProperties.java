package org.bahmni.feed.openerp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.bahmni.feed.openerp.job.Jobs;
import org.bahmni.openerp.web.OpenERPProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class OpenERPAtomFeedProperties implements OpenERPProperties {

    private static Logger logger = LoggerFactory.getLogger(OpenERPAtomFeedProperties.class);

    @Value("${chunking.strategy}")
    private String chunkingStrategy;

    @Value("${scheduler.initial.delay:60000}")
    private String schedulerInitialDelay;

    @Value("${scheduler.sale.order.cron.expression:0/30 * * * * ?}")
    private String schedulerSaleOrderCronExpression;

    public String getSchedulerInitialDelay() {
        return schedulerInitialDelay;
    }


    /**
     * @deprecated replaced by {@link #getFeedUriForJob(Jobs)} ()}
     */
    @Deprecated
    public String getFeedUri(String feedname) {
        throw new UnsupportedOperationException("This method is no longer used. Please call getFeedUriForJob() instead");
    }


    @Value("${customer.feed.generator.uri}")
    private String customFeedUri;


    @Value("${openelis.saleorder.feed.generator.uri:}")
    private String elisSaleOrderFeedUri;

    @Value("${drug.feed.generator.uri}")
    private String drugFeedUri;

    @Value("${lab.feed.generator.uri}")
    private String labFeedUri;


    @Value("${saleorder.feed.generator.uri}")
    private String saleOrderFeedUri;

    @Value("${saleable.feed.generator.uri}")
    private String saleableFeedUri;

    @Value("${drugorder.uri}")
    private String drugOrderUri;

    public String getFeedUriForJob(Jobs feedJob) {
        switch (feedJob){
            case CUSTOMER_FEED: return customFeedUri;
            case SALEORDER_FEED: return saleOrderFeedUri;
            case OPENELIS_SALEORDER_FEED: return elisSaleOrderFeedUri;
            case DRUG_FEED: return drugFeedUri;
            case LAB_TEST_FEED: return labFeedUri;
            case LAB_PANEL_FEED: return labFeedUri;
            case RADIOLOGY_TEST_FEED: return labFeedUri;
            case SALEABLE_FEED: return saleableFeedUri;
        }
        throw new RuntimeException("Can not identify feed URI for requested Job.");
    }

    public String getOdooXMLUrlEndpoint() {
        return "xmlrpc/object";
    }

    public String getOdooRESTUrlEndPoint(Jobs feedJob) {
        switch (feedJob) {
            case SALEORDER_FEED: return "/api/bahmni-saleorder";
            case SALEABLE_FEED:  return "/api/bahmni-service-sale";
            case CUSTOMER_FEED: return "/api/bahmni-customer";
            case LAB_TEST_FEED: return "/api/bahmni-lab-test";
            case LAB_PANEL_FEED: return "/api/bahmni-lab-panel";
            case RADIOLOGY_TEST_FEED: return "/api/bahmni-radiology-test";
            case DRUG_FEED: return "/api/bahmni-drug";
            default: throw new RuntimeException("Can not identify endpoint URI for requested Job.");
        }
    }

    public String getDrugOrderUri() {
        return drugOrderUri;
    }

    @Value("${openerp.host}")
    private String openErpHost;

    @Override
    public String getHost() {
        return openErpHost;
    }

    @Value("${openerp.port}")
    private String openErpPort;

    @Override
    public int getPort() {
        return Integer.parseInt(openErpPort);
    }

    @Value("${openerp.database}")
    private String openErpDatabase;

    @Override
    public String getDatabase() {
        return openErpDatabase;
    }

    @Value("${openerp.user}")
    private String openErpUser;

    @Override
    public String getUser() {
        return openErpUser;
    }

    @Value("${openerp.password}")
    private String openErpPwd;

    @Override
    public String getPassword() {
        return openErpPwd;
    }

    @Value("${openerp.connectionTimeoutInMilliseconds}")
    private String openErpConTimeOut;

    @Override
    public int getConnectionTimeoutInMilliseconds() {
        return Integer.parseInt(openErpConTimeOut);
    }

    @Value("${openerp.replyTimeoutInMilliseconds}")
    private String openErpReplyTimeOut;

    @Override
    public int getReplyTimeoutInMilliseconds() {
        return Integer.parseInt(openErpReplyTimeOut);
    }

    @Value("${openerp.maxFailedEvents}")
    private String openErpMaxFailedEvents;

    public int getMaxFailedEvents() {
        return Integer.parseInt(openErpMaxFailedEvents);
    }

    @Value("${openmrs.auth.uri}")
    private String openmrsAuthUri;

    public String getAuthenticationURI() {
        return openmrsAuthUri;
    }

    @Value("${openelis.uri:}")
    private String openElisUri;

    public String getOpenElisURI() {
        return openElisUri;
    }

    @Value("${openelis.user:}")
    private String openElisUser;

    public String getOpenElisUser() {
        return openElisUser;
    }

    @Value("${openelis.password:}")
    private String openElisPwd;

    public String getOpenElisPassword() {
        return openElisPwd;
    }

    @Value("${openmrs.user}")
    private String openmrsUser;

    public String getOpenMRSUser() {
        return openmrsUser;
    }

    @Value("${openmrs.password}")
    private String openmrsPwd;

    public String getOpenMRSPassword() {
        return openmrsPwd;
    }

    @Value("${referencedata.endpoint}")
    private String refDataEndPt;

    public String getReferenceDataEndpointURI(){
        return refDataEndPt;
    }


    @PostConstruct
    private void debug() {
        logger.debug("**************** DEBUG OpenERPAtomFeedProperties ************************ ");
        HashMap<String, String> properties = getInfo();
        for (String s : properties.keySet()) {
            logger.debug(String.format("%s=%s",s, properties.get(s)));

        }
        logger.debug("**************** DEBUG OpenERPAtomFeedProperties ************************ ");
    }



    private HashMap<String, String> getInfo() {
        HashMap<String, String> values = new HashMap<>();
        values.put("chunking.strategy",chunkingStrategy );
        values.put("scheduler.initial.delay", schedulerInitialDelay);
        values.put("scheduler.sale.order.cron.expression", schedulerSaleOrderCronExpression);
        values.put("customer.feed.generator.uri",customFeedUri );
        values.put("openelis.saleorder.feed.generator.uri",elisSaleOrderFeedUri );
        values.put("drug.feed.generator.uri",drugFeedUri );
        values.put("lab.feed.generator.uri",labFeedUri );
        values.put("saleorder.feed.generator.uri", saleOrderFeedUri);
        values.put("drugorder.uri", drugOrderUri);
        values.put("openerp.host",openErpHost );
        values.put("openerp.port",openErpPort );
        values.put("openerp.database",openErpDatabase );
        values.put("openerp.user",openErpUser );
        values.put("openerp.connectionTimeoutInMilliseconds",openErpConTimeOut );
        values.put("openerp.replyTimeoutInMilliseconds",openErpReplyTimeOut );
        values.put("openerp.maxFailedEvents",openErpMaxFailedEvents );
        values.put("openmrs.auth.uri",openmrsAuthUri );
        values.put("openelis.uri",openElisUri );
        values.put("openelis.user",openElisUser );
        values.put("openmrs.user",openmrsUser );
        values.put("referencedata.endpoint",refDataEndPt);
        values.put("saleable.feed.generator.uri", saleableFeedUri);
        return values;
    }


}
