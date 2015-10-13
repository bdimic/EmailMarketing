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
@Component("trackingResponseDao")
public class TrackingResponseDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<TrackingResponse> getAllTrackingResponse() {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        return crit.list();
    }
        
    public List<TrackingResponse> getTrackingResponseByBroadcastId(String broadcast_id) {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return crit.list();
    }
    
    public List<TrackingResponse> getTrackingResponseByEmail(String email) {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("email", email));
        return crit.list();
    }
    
    public TrackingResponse getResponseById(long id) {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("id", id));
        return (TrackingResponse)crit.uniqueResult();
    }
    
    public String saveOrUpdate(TrackingResponse trackingResponse) {
	session().saveOrUpdate(trackingResponse);
        return String.valueOf(trackingResponse.getId());
    }
    
    public boolean delete(String id) {
        long b_id = Long.parseLong(id);
        Query query = session().createQuery("delete TrackingResponse b where b.id=:id");
        query.setParameter("id", b_id);
        return query.executeUpdate() == 1;
    }
}
