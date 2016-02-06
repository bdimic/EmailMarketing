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

// TODO: Auto-generated Javadoc
/**
 * The Class TrackingResponseDao.
 */
@Repository
@Transactional
@Component("trackingResponseDao")
public class TrackingResponseDao {
    
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
     * Gets the all tracking response.
     *
     * @return the all tracking response
     */
    public List<TrackingResponse> getAllTrackingResponse() {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        return crit.list();
    }
        
    /**
     * Gets the tracking response by broadcast id.
     *
     * @param broadcast_id the broadcast_id
     * @return the tracking response by broadcast id
     */
    public List<TrackingResponse> getTrackingResponseByBroadcastId(String broadcast_id) {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return crit.list();
    }
    
    /**
     * Gets the tracking response by email.
     *
     * @param email the email
     * @return the tracking response by email
     */
    public List<TrackingResponse> getTrackingResponseByEmail(String email) {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("email", email));
        return crit.list();
    }
    
    /**
     * Gets the response by id.
     *
     * @param id the id
     * @return the response by id
     */
    public TrackingResponse getResponseById(long id) {
        Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("id", id));
        return (TrackingResponse)crit.uniqueResult();
    }
    
    /**
     * Gets the no of opens by broadcast.
     *
     * @param broadcast_id the broadcast_id
     * @return the no of opens by broadcast
     */
    public int getNoOfOpensByBroadcast(String broadcast_id) {
    	Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        crit.add(Restrictions.eq("response_type", "Open"));
        List result = crit.setProjection(Projections.projectionList().add(Projections.groupProperty("unique_id"))).list();
        return result.size();
    }
    
    /**
     * Gets the no of click by broadcast.
     *
     * @param broadcast_id the broadcast_id
     * @return the no of click by broadcast
     */
    public int getNoOfClickByBroadcast(String broadcast_id) {
    	Criteria crit = session().createCriteria(TrackingResponse.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        crit.add(Restrictions.eq("response_type", "Click"));
        List result = crit.setProjection(Projections.projectionList().add(Projections.groupProperty("unique_id"))).list();
        return result.size();
    }
    
    /**
     * Check response exists.
     *
     * @param unique_id the unique_id
     * @param broadcast_id the broadcast_id
     * @param response_type the response_type
     * @param response_source the response_source
     * @param response_url the response_url
     * @param response_time the response_time
     * @return the list
     */
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
    
    /**
     * Save or update.
     *
     * @param trackingResponse the tracking response
     * @return the string
     */
    public String saveOrUpdate(TrackingResponse trackingResponse) {
	session().saveOrUpdate(trackingResponse);
        return String.valueOf(trackingResponse.getId());
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
        long b_id = Long.parseLong(id);
        Query query = session().createQuery("delete TrackingResponse b where b.id=:id");
        query.setParameter("id", b_id);
        return query.executeUpdate() == 1;
    }
}
