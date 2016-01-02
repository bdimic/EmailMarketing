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
@Component("broadcastTemplateDao")
public class BroadcastTemplateDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<BroadcastTemplate> getAllBroadcastTemplates() {
        Criteria crit = session().createCriteria(BroadcastTemplate.class);
        return crit.list();
    }
    
    
    public BroadcastTemplate getBroadcastTemplate(String id) {
    	
        Criteria crit = session().createCriteria(BroadcastTemplate.class);
        int bt_id = Integer.parseInt(id);
        crit.add(Restrictions.eq("id", bt_id));
        return (BroadcastTemplate)crit.uniqueResult();
    }
    
    public BroadcastTemplate getBroadcastTemplateById(int id) {    	
        Criteria crit = session().createCriteria(BroadcastTemplate.class);
        crit.add(Restrictions.eq("id", id));
        return (BroadcastTemplate)crit.uniqueResult();
    }
    
    public String saveOrUpdate(BroadcastTemplate broadcastTemplate) {
	session().saveOrUpdate(broadcastTemplate);
        return String.valueOf(broadcastTemplate.getB_template_name());
    }
    
    public boolean delete(int id) {
        Query query = session().createQuery("delete BroadcastTemplate b where b.id=:id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
