/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class configDao.
 */
@Repository
@Transactional
@Component("configDao")
public class ConfigDao {
    
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
	 * Gets the config.
	 *
	 * @return the config
	 */
    public List<Config> getAllConfigs() {
        Criteria crit = session().createCriteria(Config.class);
        return crit.list();
    }
    
    public Config getConfig(String key) {
    	Criteria crit = session().createCriteria(Config.class);
        crit.add(Restrictions.eq("key", key));
        return (Config)crit.uniqueResult();
    }
    
    /**
     * Save or update.
     *
     * @param config the config
     * @return the string
     */
    public String saveOrUpdate(Config config) {
	session().saveOrUpdate(config);
        return String.valueOf(config.getId());
    }
}
