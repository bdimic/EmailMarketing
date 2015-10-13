/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailList;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bdimic
 */
@Service("sendEmail")
public class SendEmail {
	
	private ProcessVariableService processVariableService;
	private EmbeddedImageService embeddedImageService;	
	
	@Autowired
    public void setEmbeddedImageService(EmbeddedImageService embeddedImageService) {
        this.embeddedImageService = embeddedImageService;
    }
	
	@Autowired
    public void setProcessVariableService(
			ProcessVariableService processVariableService) {
		this.processVariableService = processVariableService;
	}

	public void sendEmail(Broadcast broadcast, EmailConfig emailConfig, EmailList emailList) throws EmailException, MalformedURLException, InterruptedException {   
            	
    	// email configuration part
        HtmlEmail email = new HtmlEmail();
        email.setSmtpPort(emailConfig.getPort());
        email.setHostName(emailConfig.getHostname());
        email.setAuthenticator(new DefaultAuthenticator(emailConfig.getUsername(), emailConfig.getPassword()));
        email.setSSLOnConnect(emailConfig.isSslonconnect());
        email.setDebug(emailConfig.isDebug());
        email.setFrom(emailConfig.getFrom_address()); //ovde dodati i email from description
        
//        System.out.println(emailList);
        HashMap<String, String> variables = processVariableService.ProcessVariable(emailList);
        
//        for(String keys : variables.keySet()) {
//        	System.out.println("key:"+keys+", value:"+variables.get(keys));
//        }
        
        String processSubject = broadcast.getSubject();
        String newHtml = broadcast.getHtmlbody_embed();
        String newPlainText = broadcast.getPlaintext();
        
        for(String key : variables.keySet()) {
        	processSubject = processSubject.replace("["+key+"]", variables.get(key));
//        	System.out.println(key+"-"+variables.get(key));
        	newHtml = newHtml.replace("["+key+"]", variables.get(key));
        	newPlainText = newPlainText.replace("["+key+"]", variables.get(key));
        }
//      System.out.println(processSubject);
        email.setSubject(processSubject);
        email.addTo(emailList.getEmail());
        
        String image = embeddedImageService.getEmbeddedImages(broadcast.getBroadcast_id()).getUrl();
        List<String> images = Arrays.asList(image.split(";"));
//        for(int j=0; j<images.size();j++) {
//        	System.out.println(images.get(j));
//        }
        for(int i=0; i<images.size(); i++) {
        	String id = email.embed(images.get(i), "Slika"+i);
        	newHtml = newHtml.replace("[IMAGE:"+i+"]", "cid:"+id);
        }
        
        final String SERVER_URL = "http://localhost:8080/EmailMarketing/";
        Base64 base64 = new Base64(true);
        
        Pattern pattern = Pattern.compile("<%tracking=(.*?)=tracking%>");
        Matcher matcher = pattern.matcher(newHtml);
        while (matcher.find()) {
            String url = matcher.group(1);      
            String myEncryptedUrl = new String(base64.encode(url.getBytes()));
            String oldurl = "<%tracking="+url+"=tracking%>";
            String newurl = SERVER_URL+"tracking?id="+myEncryptedUrl;
            System.out.println(newurl);
            newHtml = newHtml.replace(oldurl, newurl);
        }
//        System.out.println(newHtml);
        
        email.setHtmlMsg(newHtml);
        email.setTextMsg(newPlainText);
        try {
			email.send();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // time in seconds to wait between 2 mails
        TimeUnit.SECONDS.sleep(emailConfig.getWait());
    }
}
