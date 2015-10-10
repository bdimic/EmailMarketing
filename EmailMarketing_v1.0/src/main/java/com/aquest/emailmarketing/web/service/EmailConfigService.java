package com.aquest.emailmarketing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailConfigDao;

@Service("emailConfigService")
public class EmailConfigService {
	private EmailConfigDao emailConfigDao;

    @Autowired
    public void setEmailConfigDao(EmailConfigDao emailConfigDao) {
        this.emailConfigDao = emailConfigDao;
    }
    
    public List<EmailConfig> getAllProfiles() {
    	List<EmailConfig> emailConfigs = emailConfigDao.getAllProfiles();
    	return emailConfigs;
    }
    
    public EmailConfig getProfile(String profileName) {
    	EmailConfig emailConfig = emailConfigDao.getProfile(profileName);
    	return emailConfig;
    }
    
    public EmailConfig getProfileById(int id) {
    	EmailConfig emailConfig = emailConfigDao.getProfileById(id);
    	return emailConfig;
    }
    
    public String saveOrUpdate(EmailConfig emailConfig) {
    	return emailConfigDao.saveOrUpdate(emailConfig);
    }
    
    public boolean delete(int id) {
    	return emailConfigDao.delete(id);
    }
}
