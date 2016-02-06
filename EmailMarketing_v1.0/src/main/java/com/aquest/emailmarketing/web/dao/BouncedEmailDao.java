/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * The Class BouncedEmailDao.
 */
@Repository
@Transactional
@Component("bouncedEmailDao")
public class BouncedEmailDao {
    
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
     * Gets the all bounced emails.
     *
     * @return the all bounced emails
     */
    public List<BouncedEmail> getAllBouncedEmails() {
        Criteria crit = session().createCriteria(BouncedEmail.class);
        return crit.list();
    }
    
    /**
     * Checks if is bouncedget bounced email.
     *
     * @param email_address the email_address
     * @return the list
     */
    public List<BouncedEmail> isBouncedgetBouncedEmail(String email_address) {
    	Criteria crit = session().createCriteria(EmailList.class);
        crit.add(Restrictions.eq("email_address", email_address));
        return crit.list();
    }
    
    /**
     * Save or update.
     *
     * @param bouncedEmail the bounced email
     */
    public void saveOrUpdate(BouncedEmail bouncedEmail) {
	session().saveOrUpdate(bouncedEmail);
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        long b_id = Long.parseLong(id);
        Query query = session().createQuery("delete BouncedEmail e where b.id=:id");
        query.setParameter("id", b_id);
        return query.executeUpdate() == 1;
    }
}
