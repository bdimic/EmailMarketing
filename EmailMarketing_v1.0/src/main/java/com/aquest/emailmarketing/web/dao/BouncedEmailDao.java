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

/**
 *
 * @author bdimic
 */
@Repository
@Transactional
@Component("bouncedEmailDao")
public class BouncedEmailDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<BouncedEmail> getAllBouncedEmails() {
        Criteria crit = session().createCriteria(BouncedEmail.class);
        return crit.list();
    }
    
    public List<BouncedEmail> isBouncedgetBouncedEmail(String email_address) {
    	Criteria crit = session().createCriteria(EmailList.class);
        crit.add(Restrictions.eq("email_address", email_address));
        return crit.list();
    }
    
    public void saveOrUpdate(BouncedEmail bouncedEmail) {
	session().saveOrUpdate(bouncedEmail);
    }
    
    public boolean delete(String id) {
        long b_id = Long.parseLong(id);
        Query query = session().createQuery("delete BouncedEmail e where b.id=:id");
        query.setParameter("id", b_id);
        return query.executeUpdate() == 1;
    }
}
