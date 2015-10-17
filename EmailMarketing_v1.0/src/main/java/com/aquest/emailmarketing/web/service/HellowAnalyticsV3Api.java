package com.aquest.emailmarketing.web.service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.GaData;

public class HellowAnalyticsV3Api {

private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
private static final JsonFactory JSON_FACTORY = new JacksonFactory();
private static final String TABLE_ID = "ga:109473807";

public void analyticsExample() throws GeneralSecurityException, IOException {

    // This is the .p12 file you got from the google api console by clicking generate new key
    File analyticsKeyFile = new File("src/main/resources/EmailMarketing.p12");

    // This is the service account email address that you can find in the api console
    String apiEmail = "1336465011-ohdm4a6vilvtl22ifdvgi50q9v3lpgk1@developer.gserviceaccount.com";

    GoogleCredential credential = new GoogleCredential.Builder()
        .setTransport(HTTP_TRANSPORT)
        .setJsonFactory(JSON_FACTORY)
        .setServiceAccountId(apiEmail)
        .setServiceAccountScopes(Arrays.asList(AnalyticsScopes.ANALYTICS_READONLY))
        .setServiceAccountPrivateKeyFromP12File(analyticsKeyFile).build();

    Analytics analyticsService = new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
        .setApplicationName("EmailMarketing")
        .build();


    String startDate = "2015-10-01";
    String endDate = "2015-10-17";
    String mertrics = "ga:sessions,ga:timeOnPage";

    // Use the analytics object build a query
    Get get = analyticsService.data().ga().get(TABLE_ID, startDate, endDate, mertrics);
    get.setDimensions("ga:city");
    //get.setFilters("ga:country==Serbia");
    get.setSort("-ga:sessions");

    // Run the query
    GaData data = get.execute();

    // Do something with the data
    if (data.getRows() != null) {
        for (List<String> row : data.getRows()) {
            System.out.println(row);
        }
    }

	}
}
