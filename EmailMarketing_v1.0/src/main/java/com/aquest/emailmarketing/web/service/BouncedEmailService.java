/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Header;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.BounceCode;
import com.aquest.emailmarketing.web.dao.BouncedEmail;
import com.aquest.emailmarketing.web.dao.BouncedEmailDao;
import com.aquest.emailmarketing.web.dao.EmailConfig;

/**
 *
 * @author bdimic
 */
@Service("bouncedEmailService")
public class BouncedEmailService {
	
	private BouncedEmailDao bouncedEmailDao;
	private BounceCodeService bounceCodeService;
	private EmailConfigService emailConfigService;

	@Autowired
	public void setBouncedEmailDao(BouncedEmailDao bouncedEmailDao) {
		this.bouncedEmailDao = bouncedEmailDao;
	}
	
	@Autowired
	public void setBounceCodeService(BounceCodeService bounceCodeService) {
		this.bounceCodeService = bounceCodeService;
	}

	@Autowired
	public void setEmailConfigService(EmailConfigService emailConfigService) {
		this.emailConfigService = emailConfigService;
	}

	public List<BouncedEmail> getAllBouncedEmails() {
		return bouncedEmailDao.getAllBouncedEmails();
	}	

	public List<BouncedEmail> isBouncedgetBouncedEmail(String email_address) {
		return bouncedEmailDao.isBouncedgetBouncedEmail(email_address);
	}
	
	public void saveOrUpdate(BouncedEmail bouncedEmail) {
		bouncedEmailDao.saveOrUpdate(bouncedEmail);
	}
	
	public boolean delete(String id) {
		return bouncedEmailDao.delete(id);
	}
	
	private Properties getServerProperties(String protocol, String host,
            String port) {
        Properties properties = new Properties();
 
        // server setting
        properties.put(String.format("mail.%s.host", protocol), host);
        properties.put(String.format("mail.%s.port", protocol), port);
 
        // SSL setting
//        properties.setProperty(
//                String.format("mail.%s.socketFactory.class", protocol),
//                "javax.net.ssl.SSLSocketFactory");
//        properties.setProperty(
//                String.format("mail.%s.socketFactory.fallback", protocol),
//                "false");
//        properties.setProperty(
//                String.format("mail.%s.socketFactory.port", protocol),
//                String.valueOf(port));
 
        return properties;
    }
	
	public void processBounces(String protocol, String host, String port,
            String userName, String password) {
        Properties properties = getServerProperties(protocol, host, port);
		//Properties properties = new Properties();
		//properties.put("mail.pop3.host", "mail.aquest-solutions.com");
	    //properties.put("mail.pop3.port", "110");
		//Properties properties = getServerProperties("pop3", "mail.aquest-solutions.com", "110");
        Session session = Session.getDefaultInstance(properties);
        
        List<BounceCode> bounceCodes = bounceCodeService.getAllBounceCodes();
        
 
        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            //store.connect(userName, password);
            store.connect("bojan.dimic@aquest-solutions.com", "bg181076");
 
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
 
            // fetches new messages from server
            Message[] messages = folderInbox.getMessages();
 
            for (int i = 0; i < messages.length; i++) {
            	BouncedEmail bouncedEmail = new BouncedEmail();
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String failedAddress = "";
                Enumeration<?> headers = messages[i].getAllHeaders();
                while (headers.hasMoreElements()) {
                    Header h = (Header) headers.nextElement();
                    //System.out.println(h.getName() + ":" + h.getValue());
                    //System.out.println("");                
                    String mID = h.getName();                
                    if(mID.contains("X-Failed-Recipients")){
                    	 failedAddress = h.getValue();
                    }
                }
                String subject = msg.getSubject();
                String toList = parseAddresses(msg
                        .getRecipients(RecipientType.TO));
                String ccList = parseAddresses(msg
                        .getRecipients(RecipientType.CC));
                String sentDate = msg.getSentDate().toString();
 
                String contentType = msg.getContentType();
                String messageContent = "";
 
                if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    } catch (Exception ex) {
                        messageContent = "[Error downloading content]";
                        ex.printStackTrace();
                    }
                }
                
                String bounceReason = "Unknown reason";
                
                for(BounceCode bounceCode: bounceCodes) {
                	if(messageContent.contains(bounceCode.getCode())) {
                		bounceReason = bounceCode.getExplanation();
                	}
                }
                
//                if(messageContent.contains(" 550 ") || messageContent.contains(" 550-")) {bounceReason="User’s mailbox was unavailable (such as not found)";}
//                if(messageContent.contains(" 554 ") || messageContent.contains(" 554-")) {bounceReason="The transaction failed for some unstated reason.";}
//                
//                // enhanced bounce codes
//                if(messageContent.contains("5.0.0")) {bounceReason="Unknown issue";}
//                if(messageContent.contains("5.1.0")) {bounceReason="Other address status";}
//                if(messageContent.contains("5.1.1")) {bounceReason="Bad destination mailbox address";}
//                if(messageContent.contains("5.1.2")) {bounceReason="Bad destination system address";}
//                if(messageContent.contains("5.1.3")) {bounceReason="Bad destination mailbox address syntax";}
//                if(messageContent.contains("5.1.4")) {bounceReason="Destination mailbox address ambiguous";}
//                if(messageContent.contains("5.1.5")) {bounceReason="Destination mailbox address valid";}
//                if(messageContent.contains("5.1.6")) {bounceReason="Mailbox has moved";}
//                if(messageContent.contains("5.7.1")) {bounceReason="Delivery not authorized, message refused";}
 
                // print out details of each message
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t X-Failed-Recipients:" + failedAddress);
                System.out.println("\t Content Type:" + contentType);
                System.out.println("\t Bounce reason:" + bounceReason);
                //System.out.println("\t Message: " + messageContent);
                
                bouncedEmail.setEmail_address(failedAddress);
                bouncedEmail.setBounce_reason(bounceReason);
                
                
                
                //Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                //String strDate = date.toString();
                System.out.println(sentDate);
                Date dt = null;
                //Date nd = null;
                try {
                	dt = formatter.parse(sentDate);
                	//nd = sqlFormat.parse(dt.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("Date " + dt.toString());
                //System.out.println("Date " + nd.toString());
                System.out.println(new Timestamp(new Date().getTime()));
                bouncedEmail.setSend_date(dt);
                bouncedEmailDao.saveOrUpdate(bouncedEmail);
            }
            
 
            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }
    }
	
	public void processAllBounces() {
		List<EmailConfig> emailConfigs = emailConfigService.getAllProfiles();
		
		for(EmailConfig emailConfig: emailConfigs) {
			System.out.println(emailConfig.toString());
			processBounces(emailConfig.getBounce_protocol(), emailConfig.getBounce_host(), emailConfig.getBounce_port(), emailConfig.getBounce_username(), emailConfig.getBounce_password());
		}
	}
 
    /**
     * Returns a list of addresses in String format separated by comma
     *
     * @param address an array of Address objects
     * @return a string represents a list of addresses
     */
    private String parseAddresses(Address[] address) {
        String listAddress = "";
 
        if (address != null) {
            for (int i = 0; i < address.length; i++) {
                listAddress += address[i].toString() + ", ";
            }
        }
        if (listAddress.length() > 1) {
            listAddress = listAddress.substring(0, listAddress.length() - 2);
        }
 
        return listAddress;
    }
	
}
