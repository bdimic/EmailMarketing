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
 * The Class GaConfigDao.
 */
@Repository
@Transactional
@Component("gaConfigDao")
public class GaConfigDao {
    
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
    
//    public List<GaConfig> getAllGaConfig() {
//        Criteria crit = session().createCriteria(GaConfig.class);
//        return crit.list();
//    }
        
    /**
 * Gets the ga config.
 *
 * @return the ga config
 */
public GaConfig getGaConfig() {
        Criteria crit = session().createCriteria(GaConfig.class);
        return (GaConfig)crit.uniqueResult();
    }
    
    /**
     * Save or update.
     *
     * @param gaConfig the ga config
     * @return the string
     */
    public String saveOrUpdate(GaConfig gaConfig) {
	session().saveOrUpdate(gaConfig);
        return String.valueOf(gaConfig.getId());
    }
}
