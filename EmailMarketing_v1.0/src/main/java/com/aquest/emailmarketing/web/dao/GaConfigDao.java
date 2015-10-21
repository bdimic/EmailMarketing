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
@Component("gaConfigDao")
public class GaConfigDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
//    public List<GaConfig> getAllGaConfig() {
//        Criteria crit = session().createCriteria(GaConfig.class);
//        return crit.list();
//    }
        
    public GaConfig getGaConfig() {
        Criteria crit = session().createCriteria(GaConfig.class);
        return (GaConfig)crit.uniqueResult();
    }
    
    public String saveOrUpdate(GaConfig gaConfig) {
	session().saveOrUpdate(gaConfig);
        return String.valueOf(gaConfig.getId());
    }
}
