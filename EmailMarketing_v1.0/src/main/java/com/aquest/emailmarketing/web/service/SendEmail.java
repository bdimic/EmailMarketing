/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.Config;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailList;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class SendEmail.
 */
@Service("sendEmail")
public class SendEmail {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(com.aquest.emailmarketing.web.service.GoogleAnalyticsService.class);
	
	/** The process variable service. */
	private ProcessVariableService processVariableService;
	
	/** The embedded image service. */
	private EmbeddedImageService embeddedImageService;	
	
	/** The email list service. */
	private EmailListService emailListService;
	
	/** The config sevice. */
	private ConfigService configService;
	
	/**
	 * Sets the embedded image service.
	 *
	 * @param embeddedImageService the new embedded image service
	 */
	@Autowired
    public void setEmbeddedImageService(EmbeddedImageService embeddedImageService) {
        this.embeddedImageService = embeddedImageService;
    }
	
	/**
	 * Sets the process variable service.
	 *
	 * @param processVariableService the new process variable service
	 */
	@Autowired
    public void setProcessVariableService(
			ProcessVariableService processVariableService) {
		this.processVariableService = processVariableService;
	}

	/**
	 * Sets the email list service.
	 * 
	 * @param emailListService the emailListService to set
	 */
	@Autowired
	public void setEmailListService(EmailListService emailListService) {
		this.emailListService = emailListService;
	}
	
	

	/**
	 * Sets the config servic.
	 * 
	 * @param configService the configService to set
	 */
	@Autowired
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	/**
	 * Send email.
	 *
	 * @param broadcast the broadcast
	 * @param emailConfig the email config
	 * @param emailList the email list
	 * @throws EmailException the email exception
	 * @throws MalformedURLException the malformed url exception
	 * @throws InterruptedException the interrupted exception
	 */
	public void sendEmail(Broadcast broadcast, EmailConfig emailConfig, EmailList emailList) throws EmailException, MalformedURLException, InterruptedException {   
        
		Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	// email configuration part
        HtmlEmail email = new HtmlEmail();
        email.setSmtpPort(emailConfig.getPort());
        email.setHostName(emailConfig.getHostname());
        email.setAuthenticator(new DefaultAuthenticator(emailConfig.getUsername(), emailConfig.getPassword()));
        email.setSSLOnConnect(emailConfig.isSslonconnect());
        email.setDebug(emailConfig.isDebug());
        email.setFrom(emailConfig.getFrom_address()); //ovde dodati i email from description
        
        System.out.println(emailList);
        HashMap<String, String> variables = processVariableService.ProcessVariable(emailList);
        
        for(String keys : variables.keySet()) {
        	System.out.println("key:"+keys+", value:"+variables.get(keys));
        }
        
        String processSubject = broadcast.getSubject();
        String newHtml = broadcast.getHtmlbody_embed();
        String newPlainText = broadcast.getPlaintext();
        
        for(String key : variables.keySet()) {
        	processSubject = processSubject.replace("["+key+"]", variables.get(key));
        	System.out.println(key+"-"+variables.get(key));
        	newHtml = newHtml.replace("["+key+"]", variables.get(key));
        	newPlainText = newPlainText.replace("["+key+"]", variables.get(key));
        }
      System.out.println(processSubject);
        email.setSubject(processSubject);
        email.addTo(emailList.getEmail());
        
        
        String image = embeddedImageService.getEmbeddedImages(broadcast.getBroadcast_id()).getUrl();
        List<String> images = Arrays.asList(image.split(";"));
        for(int j=0; j<images.size();j++) {
        	System.out.println(images.get(j));
        }
        for(int i=0; i<images.size(); i++) {
        	String id = email.embed(images.get(i), "Slika"+i);
        	newHtml = newHtml.replace("[IMAGE:"+i+"]", "cid:"+id);
        }
        
        Config config = configService.getConfig("trackingurl");
        //DONE: Create jsp page for tracking server url
        String serverUrl = config.getValue();
        System.out.println(serverUrl);
        Base64 base64 = new Base64(true);
        
        Pattern pattern = Pattern.compile("<%tracking=(.*?)=tracking%>");
        Matcher matcher = pattern.matcher(newHtml);
        while (matcher.find()) {
            String url = matcher.group(1);
            System.out.println(url);
            logger.debug(url);
            String myEncryptedUrl = new String(base64.encode(url.getBytes()));
            
            String oldurl = "<%tracking="+url+"=tracking%>";
            logger.debug(oldurl);
            System.out.println(oldurl);
            String newurl = serverUrl+"tracking?id="+myEncryptedUrl;
            logger.debug(newurl);
            System.out.println(newurl);
            newHtml = newHtml.replace(oldurl, newurl);
        }
//        System.out.println(newHtml);
        
        email.setHtmlMsg(newHtml);
        email.setTextMsg(newPlainText);
        try {
			email.send();
			emailList.setStatus("SENT");
			emailList.setProcess_dttm(curTimestamp);
			emailListService.SaveOrUpdate(emailList);
		} catch (Exception e) {
			logger.error(e);
		}
        // time in seconds to wait between 2 mails
        TimeUnit.SECONDS.sleep(emailConfig.getWait());
    }
}
