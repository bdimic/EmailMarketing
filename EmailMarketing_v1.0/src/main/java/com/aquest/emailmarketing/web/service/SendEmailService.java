package com.aquest.emailmarketing.web.service;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailList;

// TODO: Auto-generated Javadoc
/**
 * The Class SendEmailService.
 */
@Service("sendEmailService")
public class SendEmailService {
	
	/** The s email. */
	private SendEmail sEmail;
	
	/** The email config service. */
	private EmailConfigService emailConfigService;
	
	/** The email list service. */
	private EmailListService emailListService;
	
	/** The exec timestamp. */
	Timestamp execTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	/**
	 * Sets the send email.
	 *
	 * @param sEmail the new send email
	 */
	@Autowired
    public void setSendEmail(SendEmail sEmail) {
        this.sEmail = sEmail;
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
	 * Sets the email config service.
	 *
	 * @param emailConfigService the new email config service
	 */
	@Autowired
    public void setEmailConfigService(EmailConfigService emailConfigService) {
        this.emailConfigService = emailConfigService;
    }
	
	/**
	 * Send emails.
	 *
	 * @param broadcast the broadcast
	 */
	public void sendEmails(Broadcast broadcast) {
		List<EmailList> emailLists = emailListService.getAllEmailList(broadcast.getBroadcast_id());
		for(int i=0;i<emailLists.size();i++) {
			System.out.println(emailLists.get(i).getEmail());
		}
		EmailConfig emailConfig = emailConfigService.getProfileById(broadcast.getProfile_id());
		System.out.println(emailConfig);
		for(int i = 0; i<emailLists.size(); i++) {
			EmailList emailList = emailLists.get(i);
			try {
				System.out.println(emailList);
				sEmail.sendEmail(broadcast, emailConfig, emailList);
			} catch (MalformedURLException | EmailException
					| InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
