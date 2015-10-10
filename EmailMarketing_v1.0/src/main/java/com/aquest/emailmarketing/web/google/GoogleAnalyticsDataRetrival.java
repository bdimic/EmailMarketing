/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.google;

import com.google.gdata.client.analytics.AnalyticsService;
import com.google.gdata.data.analytics.AccountEntry;
import com.google.gdata.data.analytics.AccountFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author bdimic
 */
public class GoogleAnalyticsDataRetrival {
    
    // Credentials for ClientLogin Authorization
    // getting credentials from application.properties file
    //@Value("${google.user}")
    private String googleuser = "unicreditbankserbia@gmail.com";
    
    //@Value("${google.pass}")
    private String googlepass = "banka na dugme";
    
    public AccountFeed getAccountFeed() throws AuthenticationException, IOException, ServiceException {
      
        System.out.println(googleuser);
        System.out.println(googlepass);
        // Service Object to work with the Google Analytics Data Export API.
        AnalyticsService analyticsService = new AnalyticsService("Test Google Report v1.0");
      
        // Client Login Authorization.
        analyticsService.setUserCredentials(googleuser, googlepass);
      
        // Construct query from a string.
        URL queryUrl = new URL(
            "https://www.google.com/analytics/feeds/accounts/default?max-results=50");
      
        // Make request to the API.
        AccountFeed accountFeed = analyticsService.getFeed(queryUrl, AccountFeed.class);
        
         // Output the data to the screen.
    System.out.println("-------- Account Feed Results --------");
    for (AccountEntry entry : accountFeed.getEntries()) {
      System.out.println(
        "\nAccount Name  = " + entry.getProperty("ga:accountName") +
        "\nView (Profile) Name  = " + entry.getTitle().getPlainText() +
        "\nView (Profile) Id    = " + entry.getProperty("ga:profileId") +
        "\nTable Id      = " + entry.getTableId().getValue());
    }

        
        return accountFeed;      
    }
}
