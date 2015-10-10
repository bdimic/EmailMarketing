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

@Service("sendEmailService")
public class SendEmailService {
	
	private SendEmail sEmail;
	private EmailConfigService emailConfigService;
	private EmailListService emailListService;
	Timestamp execTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	
	@Autowired
    public void setSendEmail(SendEmail sEmail) {
        this.sEmail = sEmail;
    }
	
	@Autowired	
	public void setEmailListService(EmailListService emailListService) {
		this.emailListService = emailListService;
	}

	@Autowired
    public void setEmailConfigService(EmailConfigService emailConfigService) {
        this.emailConfigService = emailConfigService;
    }
	
	public void sendEmails(Broadcast broadcast) {
		List<EmailList> emailLists = emailListService.getAllEmailList(broadcast.getBroadcast_id());
//		for(int i=0;i<emailLists.size();i++) {
//			System.out.println(emailLists.get(i).getEmail());
//		}
		EmailConfig emailConfig = emailConfigService.getProfileById(broadcast.getProfile_id());
//		System.out.println(emailConfig);
		for(int i = 0; i<emailLists.size(); i++) {
			EmailList emailList = emailLists.get(i);
			try {
//				System.out.println(emailList);
				sEmail.sendEmail(broadcast, emailConfig, emailList);
			} catch (MalformedURLException | EmailException
					| InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
