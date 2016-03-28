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

// TODO: Auto-generated Javadoc
/**
 * The Class BouncedEmailService.
 */
@Service("bouncedEmailService")
public class BouncedEmailService {
	
	/** The bounced email dao. */
	private BouncedEmailDao bouncedEmailDao;
	
	/** The bounce code service. */
	private BounceCodeService bounceCodeService;
	
	/** The email config service. */
	private EmailConfigService emailConfigService;

	/**
	 * Sets the bounced email dao.
	 *
	 * @param bouncedEmailDao the new bounced email dao
	 */
	@Autowired
	public void setBouncedEmailDao(BouncedEmailDao bouncedEmailDao) {
		this.bouncedEmailDao = bouncedEmailDao;
	}
	
	/**
	 * Sets the bounce code service.
	 *
	 * @param bounceCodeService the new bounce code service
	 */
	@Autowired
	public void setBounceCodeService(BounceCodeService bounceCodeService) {
		this.bounceCodeService = bounceCodeService;
	}

	/**
	 * Sets the email config service.
	 *
	 * @param emailConfigService the new email config service
	 */
	@Autowired
	public void setEmailConfigService(EmailConfigService emailConfigService) {
		this.emailConfigService = emailConfigService;
	}

	/**
	 * Gets the all bounced emails.
	 *
	 * @return the all bounced emails
	 */
	public List<BouncedEmail> getAllBouncedEmails() {
		return bouncedEmailDao.getAllBouncedEmails();
	}	

	/**
	 * Checks if is bouncedget bounced email.
	 *
	 * @param email_address the email_address
	 * @return the list
	 */
	public List<BouncedEmail> isBouncedgetBouncedEmail(String email_address) {
		return bouncedEmailDao.isBouncedgetBouncedEmail(email_address);
	}
	
	/**
	 * Save or update.
	 *
	 * @param bouncedEmail the bounced email
	 */
	public void saveOrUpdate(BouncedEmail bouncedEmail) {
		bouncedEmailDao.saveOrUpdate(bouncedEmail);
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean delete(String id) {
		return bouncedEmailDao.delete(id);
	}
	
	/**
	 * Gets the server properties.
	 *
	 * @param protocol the protocol
	 * @param host the host
	 * @param port the port
	 * @return the server properties
	 */
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
	
	/**
	 * Process bounces.
	 *
	 * @param protocol the protocol
	 * @param host the host
	 * @param port the port
	 * @param userName the user name
	 * @param password the password
	 */
	public void processBounces(String protocol, String host, String port,
            String userName, String password) {
        Properties properties = getServerProperties(protocol, host, port);
        Session session = Session.getDefaultInstance(properties);
        
        List<BounceCode> bounceCodes = bounceCodeService.getAllBounceCodes();
        
 
        try {
            // connects to the message store
            Store store = session.getStore(protocol);
            System.out.println(userName);
            System.out.println(password);
            System.out.println(userName.length());
            System.out.println(password.length());
            //store.connect(userName, password);
            //TODO: ispraviti username i password da idu iz poziva a ne hardcoded
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
	
	/**
	 * Process all bounces.
	 */
	public void processAllBounces() {
		List<EmailConfig> emailConfigs = emailConfigService.getAllProfiles();
		
		for(EmailConfig emailConfig: emailConfigs) {
			System.out.println(emailConfig.toString());
			processBounces(emailConfig.getBounce_protocol(), emailConfig.getBounce_host(), emailConfig.getBounce_port(), emailConfig.getBounce_username(), emailConfig.getBounce_password());
		}
	}
 
    /**
     * Parses the addresses.
     *
     * @param address the address
     * @return the string
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
