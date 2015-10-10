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

@Repository
@Transactional
@Component("emailConfigDao")
public class EmailConfigDao {
	 @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<EmailConfig> getAllProfiles() {
    	Criteria crit = session().createCriteria(EmailConfig.class);
    	return crit.list();    	
    }
    
    public EmailConfig getProfile(String profileName) {
    	Criteria crit = session().createCriteria(EmailConfig.class);
    	crit.add(Restrictions.eq("profile_name", profileName));
        return (EmailConfig)crit.uniqueResult();
    }  
    
    public EmailConfig getProfileById(int profile_id) {
    	Criteria crit = session().createCriteria(EmailConfig.class);
    	crit.add(Restrictions.eq("profile_id", profile_id));
        return (EmailConfig)crit.uniqueResult();
    }

    public String saveOrUpdate(EmailConfig emailConfig) {
	session().saveOrUpdate(emailConfig);
        return String.valueOf(emailConfig.getProfile_id());
    }
    
    public boolean delete(int profile_id) {
        Query query = session().createQuery("delete EmailConfig b where b.profile_id=:profile_id");
        query.setParameter("profile_id", profile_id);
        return query.executeUpdate() == 1;
    }
}
