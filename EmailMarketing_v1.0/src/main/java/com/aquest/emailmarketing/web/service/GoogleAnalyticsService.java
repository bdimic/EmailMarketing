package com.aquest.emailmarketing.web.service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;



@Service("googleAnalyticsService")
public class GoogleAnalyticsService {
	
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	public Map<String, String> checkGaConfig(String applicationName, String apiEmail, String KeyFileLocation) {
		 Map<String, String> result = new HashMap<String, String>();
		
		 GoogleCredential credential = null;
		 try {
			credential = new GoogleCredential.Builder()
			        .setTransport(HTTP_TRANSPORT)
			        .setJsonFactory(JSON_FACTORY)
			        .setServiceAccountId(apiEmail)
			        .setServiceAccountScopes(Arrays.asList(AnalyticsScopes.ANALYTICS_READONLY))
			        .setServiceAccountPrivateKeyFromP12File(new File(KeyFileLocation)).build();

		 } catch (GeneralSecurityException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();  
	     }	
	     // Set up and return Google Analytics API client.
	     Analytics analytics = new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
	    		 applicationName).build();

	     String profileId = "";
	     try {
	         profileId = getFirstProfileId(analytics);
	     } catch (IOException e) {
	        e.printStackTrace(); 
	     }
	     
	     if(profileId.equals("1")) {
	    	 result.put("result", "No accounts found");
	     } else if(profileId.equals("2")) {
	    	 result.put("result", "No Webproperties found");
	     } else if(profileId.equals("3")) {
	    	 result.put("result", "No profiles found");
	     } else {
	    	 result.put("result", "success");
	    	 result.put("profileId", profileId);
		}
		return result;
	}
	
	public String getFirstProfileId(Analytics analytics) throws IOException {
	    String profileId = null;

	    // Query accounts collection.
	    Accounts accounts = analytics.management().accounts().list().execute();

	    if (accounts.getItems().isEmpty()) {
	    	profileId = "1";
	    } else {
	        String firstAccountId = accounts.getItems().get(0).getId();

	        // Query webproperties collection.
	        Webproperties webproperties =
	                analytics.management().webproperties().list(firstAccountId).execute();

	        if (webproperties.getItems().isEmpty()) {
	        	profileId = "2";
	        } else {
	            String firstWebpropertyId = webproperties.getItems().get(0).getId();

	            // Query profiles collection.
	            Profiles profiles =
	                    analytics.management().profiles().list(firstAccountId, firstWebpropertyId).execute();

	            if (profiles.getItems().isEmpty()) {
	                profileId = "3";
	            } else {
	                profileId = profiles.getItems().get(0).getId();
	            }
	        }
	    }
	    return profileId;
	}
}
