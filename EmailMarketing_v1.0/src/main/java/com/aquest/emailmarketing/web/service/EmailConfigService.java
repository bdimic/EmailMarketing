package com.aquest.emailmarketing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailConfigDao;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailConfigService.
 */
@Service("emailConfigService")
public class EmailConfigService {
	
	/** The email config dao. */
	private EmailConfigDao emailConfigDao;

    /**
     * Sets the email config dao.
     *
     * @param emailConfigDao the new email config dao
     */
    @Autowired
    public void setEmailConfigDao(EmailConfigDao emailConfigDao) {
        this.emailConfigDao = emailConfigDao;
    }
    
    /**
     * Gets the first profile.
     *
     * @return the first profile
     */
    public EmailConfig getFirstProfile() {
    	List<EmailConfig> emailConfigs = emailConfigDao.getAllProfiles();    	
    	return emailConfigs.get(0);
    }
    
    /**
     * Gets the all profiles.
     *
     * @return the all profiles
     */
    public List<EmailConfig> getAllProfiles() {
    	List<EmailConfig> emailConfigs = emailConfigDao.getAllProfiles();
    	return emailConfigs;
    }
    
    /**
     * Gets the profile.
     *
     * @param profileName the profile name
     * @return the profile
     */
    public EmailConfig getProfile(String profileName) {
    	EmailConfig emailConfig = emailConfigDao.getProfile(profileName);
    	return emailConfig;
    }
    
    /**
     * Gets the profile by id.
     *
     * @param id the id
     * @return the profile by id
     */
    public EmailConfig getProfileById(int id) {
    	EmailConfig emailConfig = emailConfigDao.getProfileById(id);
    	return emailConfig;
    }
    
    /**
     * Save or update.
     *
     * @param emailConfig the email config
     * @return the string
     */
    public String saveOrUpdate(EmailConfig emailConfig) {
    	return emailConfigDao.saveOrUpdate(emailConfig);
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(int id) {
    	return emailConfigDao.delete(id);
    }
}
