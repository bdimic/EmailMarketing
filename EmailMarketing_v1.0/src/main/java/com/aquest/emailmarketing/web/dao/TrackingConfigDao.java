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
@Component("trackingConfigDao")
public class TrackingConfigDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<TrackingConfig> getAllTrackingConfig() {
        Criteria crit = session().createCriteria(TrackingConfig.class);
        return crit.list();
    }
        
    public TrackingConfig getTrackingConfig(String broadcast_id) {
        Criteria crit = session().createCriteria(TrackingConfig.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return (TrackingConfig)crit.uniqueResult();
    }
    
    public TrackingConfig getConfigById(int id) {
        Criteria crit = session().createCriteria(TrackingConfig.class);
        crit.add(Restrictions.eq("id", id));
        return (TrackingConfig)crit.uniqueResult();
    }
    
    public String saveOrUpdate(TrackingConfig trackingConfig) {
	session().saveOrUpdate(trackingConfig);
        return String.valueOf(trackingConfig.getBroadcast_id());
    }
    
    public boolean delete(String id) {
        Query query = session().createQuery("delete TrackingConfig b where b.broadcast_id=:id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
