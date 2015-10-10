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
public class EmailGoogleAnalyticsService {
    public String addTrackingToUrl(String text, Urls urls) {
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
}
