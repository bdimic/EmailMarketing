/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;


import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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
    
    public int getNoOfOpensByBroadcast(String broadcast_id) {
    	Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        crit.add(Restrictions.eq("response_type", "Open"));
        List result = crit.setProjection(Projections.projectionList().add(Projections.groupProperty("unique_id"))).list();
        return result.size();
    }
    
    public int getNoOfClickByBroadcast(String broadcast_id) {
    	Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        crit.add(Restrictions.eq("response_type", "Click"));
        List result = crit.setProjection(Projections.projectionList().add(Projections.groupProperty("unique_id"))).list();
        return result.size();
    }
    
    public List<TrackingResponse> checkResponseExists(String unique_id, String broadcast_id, String response_type, String response_source, String response_url, Timestamp response_time) {
    	Criteria crit = session().createCriteria(TrackingResponse.class);
    	long u_id = Long.parseLong(unique_id);
    	System.out.println(u_id+" "+response_time+" "+broadcast_id+" "+response_type+" "+response_source+" "+response_url);
    	crit.add(Restrictions.eq("unique_id", u_id));
    	crit.add(Restrictions.eq("broadcast_id", broadcast_id));
    	crit.add(Restrictions.eq("response_type", response_type));
    	crit.add(Restrictions.eq("response_source", response_source));
    	if(response_type.equals("Click")) {
    	   crit.add(Restrictions.eq("response_url", response_url));
    	}
    	crit.add(Restrictions.eq("response_time", response_time));
    	System.out.println(crit.list());
    	return crit.list();
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
