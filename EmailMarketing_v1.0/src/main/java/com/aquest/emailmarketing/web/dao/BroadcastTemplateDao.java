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
 * The Class BroadcastTemplateDao.
 */
@Repository
@Transactional
@Component("broadcastTemplateDao")
public class BroadcastTemplateDao {
    
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
     * Gets the all broadcast templates.
     *
     * @return the all broadcast templates
     */
    public List<BroadcastTemplate> getAllBroadcastTemplates() {
        Criteria crit = session().createCriteria(BroadcastTemplate.class);
        return crit.list();
    }
    
    
    /**
     * Gets the broadcast template.
     *
     * @param id the id
     * @return the broadcast template
     */
    public BroadcastTemplate getBroadcastTemplate(String id) {
    	
        Criteria crit = session().createCriteria(BroadcastTemplate.class);
        int bt_id = Integer.parseInt(id);
        crit.add(Restrictions.eq("id", bt_id));
        return (BroadcastTemplate)crit.uniqueResult();
    }
    
    /**
     * Gets the broadcast template by id.
     *
     * @param id the id
     * @return the broadcast template by id
     */
    public BroadcastTemplate getBroadcastTemplateById(int id) {    	
        Criteria crit = session().createCriteria(BroadcastTemplate.class);
        crit.add(Restrictions.eq("id", id));
        return (BroadcastTemplate)crit.uniqueResult();
    }
    
    /**
     * Save or update.
     *
     * @param broadcastTemplate the broadcast template
     * @return the string
     */
    public String saveOrUpdate(BroadcastTemplate broadcastTemplate) {
	session().saveOrUpdate(broadcastTemplate);
        return String.valueOf(broadcastTemplate.getB_template_name());
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(int id) {
        Query query = session().createQuery("delete BroadcastTemplate b where b.id=:id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
