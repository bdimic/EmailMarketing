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
 * The Class EmailListDao.
 */
@Repository
@Transactional
@Component("emailListDao")
public class EmailListDao {
    
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
     * Gets the all email list.
     *
     * @param broadcast_id the broadcast_id
     * @return the all email list
     */
    public List<EmailList> getAllEmailList(String broadcast_id) {
        Criteria crit = session().createCriteria(EmailList.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return crit.list();
    }
    
    /**
     * Gets the all email list.
     *
     * @param broadcast_id the broadcast_id
     * @return the all email list
     */
    public List<EmailList> getSentEmailList(String broadcast_id) {
        Criteria crit = session().createCriteria(EmailList.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        crit.add(Restrictions.eq("status", "SENT"));
        return crit.list();
    }
    
    /**
     * Gets the email list by id.
     *
     * @param id the id
     * @return the email list by id
     */
    public EmailList getEmailListById(String id) {
    	Criteria crit = session().createCriteria(EmailList.class);
    	long b_id = Long.parseLong(id);
        crit.add(Restrictions.eq("id", b_id));
        return (EmailList)crit.uniqueResult();
    }
    
    /**
     * Save or update.
     *
     * @param emailList the email list
     * @return the string
     */
    public String saveOrUpdate(EmailList emailList) {
    	session().saveOrUpdate(emailList);
        return String.valueOf(emailList.getId());
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        long b_id = Long.parseLong(id);
        Query query = session().createQuery("delete EmailList e where b.id=:id");
        query.setParameter("id", b_id);
        return query.executeUpdate() == 1;
    }
    
    public boolean exists(String broadcast_id, String email) {
    	Criteria crit = session().createCriteria(EmailList.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        crit.add(Restrictions.eq("email", email));
        EmailList emailList = (EmailList)crit.uniqueResult();
    	return emailList != null;
    }
}
