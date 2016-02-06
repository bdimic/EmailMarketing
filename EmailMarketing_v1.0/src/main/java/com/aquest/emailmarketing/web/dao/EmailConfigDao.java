package com.aquest.emailmarketing.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailConfigDao.
 */
@Repository
@Transactional
@Component("emailConfigDao")
public class EmailConfigDao {
	 
 	/** The session factory. */
 	@Autowired
    private SessionFactory sessionFactory;
    
    /**
     * Session.
     *
     * @return the session
     */
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    /**
     * Gets the all profiles.
     *
     * @return the all profiles
     */
    public List<EmailConfig> getAllProfiles() {
    	Criteria crit = session().createCriteria(EmailConfig.class);
    	return crit.list();    	
    }
    
    /**
     * Gets the profile.
     *
     * @param profileName the profile name
     * @return the profile
     */
    public EmailConfig getProfile(String profileName) {
    	Criteria crit = session().createCriteria(EmailConfig.class);
    	crit.add(Restrictions.eq("profile_name", profileName));
        return (EmailConfig)crit.uniqueResult();
    }  
    
    /**
     * Gets the profile by id.
     *
     * @param profile_id the profile_id
     * @return the profile by id
     */
    public EmailConfig getProfileById(int profile_id) {
    	Criteria crit = session().createCriteria(EmailConfig.class);
    	crit.add(Restrictions.eq("profile_id", profile_id));
        return (EmailConfig)crit.uniqueResult();
    }

    /**
     * Save or update.
     *
     * @param emailConfig the email config
     * @return the string
     */
    public String saveOrUpdate(EmailConfig emailConfig) {
	session().saveOrUpdate(emailConfig);
        return String.valueOf(emailConfig.getProfile_id());
    }
    
    /**
     * Delete.
     *
     * @param profile_id the profile_id
     * @return true, if successful
     */
    public boolean delete(int profile_id) {
        Query query = session().createQuery("delete EmailConfig b where b.profile_id=:profile_id");
        query.setParameter("profile_id", profile_id);
        return query.executeUpdate() == 1;
    }
}
