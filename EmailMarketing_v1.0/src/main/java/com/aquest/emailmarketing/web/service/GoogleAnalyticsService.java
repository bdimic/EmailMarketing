package com.aquest.emailmarketing.web.service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.dao.GaConfig;
import com.aquest.emailmarketing.web.dao.TrackingResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;



// TODO: Auto-generated Javadoc
/**
 * The Class GoogleAnalyticsService.
 */
@Service("googleAnalyticsService")
public class GoogleAnalyticsService {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(com.aquest.emailmarketing.web.service.GoogleAnalyticsService.class);
	
	/** The Constant HTTP_TRANSPORT. */
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	
	/** The Constant JSON_FACTORY. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	
	/** The ga config service. */
	private GaConfigService gaConfigService;
	
	/** The tracking response sevice. */
	private TrackingResponseService trackingResponseSevice;
	
	/** The email list service. */
	private EmailListService emailListService;
	
	/**
	 * Sets the ga config service.
	 *
	 * @param gaConfigService the new ga config service
	 */
	@Autowired	
	public void setGaConfigService(GaConfigService gaConfigService) {
		this.gaConfigService = gaConfigService;
	}
	
	/**
	 * Sets the tracking response sevice.
	 *
	 * @param trackingResponseSevice the new tracking response sevice
	 */
	@Autowired
	public void setTrackingResponseSevice(TrackingResponseService trackingResponseSevice) {
		this.trackingResponseSevice = trackingResponseSevice;
	}
	
	/**
	 * Sets the email list service.
	 *
	 * @param emailListService the new email list service
	 */
	@Autowired
	public void setEmailListService(EmailListService emailListService) {
		this.emailListService = emailListService;
	}

	/**
	 * Check ga config.
	 *
	 * @param applicationName the application name
	 * @param apiEmail the api email
	 * @param KeyFileLocation the key file location
	 * @return the map
	 */
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
	         logger.error(e);
	     } catch (IOException e) {
	    	 logger.error(e);  
	     }	
	     // Set up and return Google Analytics API client.
	     Analytics analytics = new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
	    		 applicationName).build();

	     String profileId = "";
	     try {
	         profileId = getFirstProfileId(analytics);
	     } catch (IOException e) {
	    	 logger.error(e); 
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
	
	/**
	 * Gets the first profile id.
	 *
	 * @param analytics the analytics
	 * @return the first profile id
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Gets the ga click responses.
	 *
	 * @return the ga click responses
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void getGaClickResponses() throws IOException {
		GaConfig gaConfig = gaConfigService.getGaConfig();
		
		Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
		GoogleCredential credential = null;
		 try {
			credential = new GoogleCredential.Builder()
			        .setTransport(HTTP_TRANSPORT)
			        .setJsonFactory(JSON_FACTORY)
			        .setServiceAccountId(gaConfig.getApi_email())
			        .setServiceAccountScopes(Arrays.asList(AnalyticsScopes.ANALYTICS_READONLY))
			        .setServiceAccountPrivateKeyFromP12File(new File(gaConfig.getP12_key_file_name())).build();

		 } catch (GeneralSecurityException e) {
			 logger.error(e);
	     } catch (IOException e) {
	    	 logger.error(e);  
	     }	
	     // Set up and return Google Analytics API client.
	     Analytics analytics = new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
	    		 gaConfig.getApplication_name()).build();
	     
	     String startDate = "7daysAgo";
	     String endDate = "today";
	     String mertrics = "ga:pageviews";

	     // Use the analytics object build a query
	     Get get = analytics.data().ga().get("ga:"+gaConfig.getTable_id(), startDate, endDate, mertrics);
	     get.setDimensions("ga:date, ga:campaign, ga:source, ga:adContent, ga:pageTitle");
	     get.setFilters("ga:medium==email");

	     // Run the query
	     GaData data = get.execute();
	     

	     if (data.getRows() != null) {
	     	
	     	// Print column headers.
	         for (GaData.ColumnHeaders header : data.getColumnHeaders()) {
	             System.out.println(header.getName());
	         }
	     	
	         for (List<String> row : data.getRows()) {
	             System.out.println(row);
	             try {
	            	Date tradeDate = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH).parse(row.get(0));
		 			String krwtrDate = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(tradeDate);
		 			Timestamp ts = Timestamp.valueOf(krwtrDate);
	 				List<TrackingResponse> ifResponseExists = trackingResponseSevice.checkResponseExists(row.get(3), row.get(1), "Click", "Google Analytics", row.get(4), ts);
	 				System.out.println(ifResponseExists);
	 				if(ifResponseExists.isEmpty()) {
	 					EmailList emailList = emailListService.getEmailListById(row.get(3));
	 					System.out.println(emailList);
	 					
	 					if(emailList != null) {
		 					TrackingResponse trackingResponse = new TrackingResponse();
		 					trackingResponse.setUnique_id(Long.parseLong(row.get(3)));
		 					trackingResponse.setBroadcast_id(row.get(1));
		 					trackingResponse.setEmail(emailList.getEmail());
		 					trackingResponse.setResponse_type("Click");
		 					trackingResponse.setResponse_source("Google Analytics");
		 					trackingResponse.setResponse_url(row.get(4));
		 					trackingResponse.setResponse_time(Timestamp.valueOf(krwtrDate));
		 					trackingResponse.setProcessed_dttm(curTimestamp);
		 					trackingResponseSevice.SaveOrUpdate(trackingResponse);
	 					}
	 				}
	 			} catch (ParseException e) {
	 				logger.error(e);
	 			}
	         }
	     } else {
	     	System.out.println("Nema podataka!");
	     }
	}
	
	/**
	 * Gets the ga open responses.
	 *
	 * @return the ga open responses
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void getGaOpenResponses() throws IOException {
		GaConfig gaConfig = gaConfigService.getGaConfig();
		
		Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		
		GoogleCredential credential = null;
		 try {
			credential = new GoogleCredential.Builder()
			        .setTransport(HTTP_TRANSPORT)
			        .setJsonFactory(JSON_FACTORY)
			        .setServiceAccountId(gaConfig.getApi_email())
			        .setServiceAccountScopes(Arrays.asList(AnalyticsScopes.ANALYTICS_READONLY))
			        .setServiceAccountPrivateKeyFromP12File(new File(gaConfig.getP12_key_file_name())).build();

		 } catch (GeneralSecurityException e) {
			 logger.error(e);
	     } catch (IOException e) {
	    	 logger.error(e);  
	     }	
	     // Set up and return Google Analytics API client.
	     Analytics analytics = new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
	    		 gaConfig.getApplication_name()).build();
	     
	     String startDate = "7daysAgo";
	     String endDate = "today";
	     String mertrics = "ga:totalEvents, ga:uniqueEvents";

	     // Use the analytics object build a query
	     Get get = analytics.data().ga().get("ga:"+gaConfig.getTable_id(), startDate, endDate, mertrics);
	     get.setDimensions("ga:date, ga:eventLabel,ga:source,ga:campaign");
	     get.setFilters("ga:eventCategory==email");
	     get.setFilters("ga:eventAction==open");

	     // Run the query
	     GaData data = get.execute();
	     
	     if (data.getRows() != null) {
	     	
	     	// Print column headers.
	         for (GaData.ColumnHeaders header : data.getColumnHeaders()) {
	             System.out.println(header.getName());
	         }
	     	
	         for (List<String> row : data.getRows()) {
	             System.out.println(row);
	             try {
	            	Date tradeDate = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH).parse(row.get(0));
	 				String krwtrDate = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(tradeDate);
	 				Timestamp ts = Timestamp.valueOf(krwtrDate);
	 				List<TrackingResponse> ifResponseExists = trackingResponseSevice.checkResponseExists(row.get(1), row.get(3), "Open", "Google Analytics", "", ts);
	 				System.out.println(ifResponseExists);
	 				if(ifResponseExists.isEmpty()) {
	 					EmailList emailList = emailListService.getEmailListById(row.get(1));
	 					
	 					if(emailList != null) {
		 					TrackingResponse trackingResponse = new TrackingResponse();
		 					trackingResponse.setUnique_id(Long.parseLong(row.get(1)));
		 					trackingResponse.setBroadcast_id(row.get(3));
		 					trackingResponse.setEmail(emailList.getEmail());
		 					trackingResponse.setResponse_type("Open");
		 					trackingResponse.setResponse_source("Google Analytics");
		 					trackingResponse.setResponse_time(Timestamp.valueOf(krwtrDate));
		 					trackingResponse.setProcessed_dttm(curTimestamp);
		 					trackingResponseSevice.SaveOrUpdate(trackingResponse);
	 					}
	 				}
	 			} catch (ParseException e) {
	 				logger.error(e);
	 			}
	         }
	     } else {
	     	logger.debug("No data available");
	     }
	}
	
	/**
	 * Gets the ga responses.
	 *
	 * @return the ga responses
	 */
	public void getGaResponses() {
		try {
			getGaOpenResponses();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			getGaClickResponses();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
