/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.tracking;

import com.aquest.emailmarketing.web.dao.Urls;

/**
 *
 * @author bdimic
 */
public class EmailTrackingService {
	
	private final String SERVER_URL = "localhost:8080/EmailMarketing/"; // samo privremeno
	// ovo mora da bude definisano u nekoj config tabeli
	private final String GA_TRACKING_ID = "UA-68393999-1"; //samo privremeno
	// ovde mora da se doda citanje google analytics tracking ID-ja (tid)
	// Svi parametri za google analytics treba da se upisuju u tabelu
	// za aQuest-solutions je: UA-68393999-1
	
	//GA Open Email Tracking
	public String addGaOpenEmailTracking(String text, Urls urls) {
		String imgOpenTrack = "<img src=\"http://www.google-analytics.com/collect?v=1&tid="+GA_TRACKING_ID+"&cid="+urls.getUtmContent()+"&t=event&ec=email&ea=open&el="+urls.getUtmContent()+"&cs="+urls.getUtmSource()+"&cm=email&cn="+urls.getUtmCampaign()+"\" /></body>";
		String addedGaOpen = text.replace("</body>", imgOpenTrack);
		return addedGaOpen;
	}
	//Pixel Image Open Email Tracking
	public String addPixelOpenEmailTracking(String text) {
		String imgOpenTrack = "<img src=\"http://"+SERVER_URL+"openTrack?trackingId=[UNIQUE_ID]\" /></body>";
		String addedPixelOpen = text.replace("</body>", imgOpenTrack);
		return addedPixelOpen;
	}
	//Internal Click Tracking	
	public String addIntTrackingToUrl(String text, Urls urls) {
	    String addedTracking = text;
	    for(int j= 0; j<urls.getUrl().length;j++) {
	    	System.out.println(urls.getUrl()[j]);
	    }
	    
	    for(int i= 0; i<urls.getUrl().length;i++) {
	    	String url = urls.getUrl()[i];
	    	String start = "?";
	    	if(url.contains("?")) {
	    		start = "&";
	    	}
	    	String urlnew = "<%tracking="+url+start+"trackingId=[UNIQUE_ID]=tracking%>";
	    	addedTracking = addedTracking.replace(url, urlnew);
	    }
	    System.out.println(addedTracking);
	    return addedTracking;
	    }
	//GA Click Tracking
    public String addGaTrackingToUrl(String text, Urls urls) {
    String addedTracking = text;
    for(int j= 0; j<urls.getUrl().length;j++) {
    	System.out.println(urls.getUrl()[j]);
    }
    
    for(int i= 0; i<urls.getUrl().length;i++) {
    	String url = "href=\""+urls.getUrl()[i];
    	String start = "?";
    	if(url.contains("?")) {
    		start = "&";
    	}
    	String urlnew = url+start+"utm_campaign="+urls.getUtmCampaign()+"&utm_medium="+urls.getUtmMedium()+"&utm_source="+urls.getUtmSource()+"&utm_content="+urls.getUtmContent();
    	addedTracking = addedTracking.replace(url+"\"", urlnew+"\"");
    }
    System.out.println(addedTracking);
    return addedTracking;
    }
    
    //Both (GA and Internal Tracking) Tracking
    public String addBothTrackingToUrl(String text, Urls urls) {
        String addedTracking = text;
        for(int j= 0; j<urls.getUrl().length;j++) {
        	System.out.println(urls.getUrl()[j]);
        }
        
        for(int i= 0; i<urls.getUrl().length;i++) {
        	String url = "href=\""+urls.getUrl()[i];
        	String start = "?";
        	if(url.contains("?")) {
        		start = "&";
        	}
        	String urlGa = url+start+"utm_campaign="+urls.getUtmCampaign()+"&utm_medium="+urls.getUtmMedium()+"&utm_source="+urls.getUtmSource()+"&utm_content="+urls.getUtmContent();
        	String urlnew = "<%tracking="+urlGa+"&trackingId=[UNIQUE_ID]=tracking%>";
        	addedTracking = addedTracking.replace(url+"\"", urlnew+"\"");
        }
        
        System.out.println(addedTracking);
        return addedTracking;
    }
}
