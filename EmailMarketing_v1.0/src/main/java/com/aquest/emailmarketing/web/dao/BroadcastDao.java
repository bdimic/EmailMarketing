/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
 * The Class BroadcastDao.
 */
@Repository
@Transactional
@Component("broadcastDao")
public class BroadcastDao {
    
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(com.aquest.emailmarketing.web.dao.BroadcastDao.class);
	
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
     * Gets the all broadcasts.
     *
     * @return the all broadcasts
     */
    public List<Broadcast> getAllBroadcasts() {
        Criteria crit = session().createCriteria(Broadcast.class);
        return crit.list();
    }
    
    /**
     * Gets the next broadcast id.
     *
     * @return the next broadcast id
     */
    public String getNextBroadcastId() {
    	int broadcastid = 1000000;
    	try {
			Query query = session().createQuery("select max(cast(substring(b.broadcast_id,3) AS int)) from Broadcast b where b.broadcast_source='EM'");
			List currentSeq = query.list();
			if(currentSeq.get(0) == null) {
				return "EM"+String.valueOf(broadcastid);
			} else {
				broadcastid = (Integer)currentSeq.get(0)+1;
				return "EM"+String.valueOf(broadcastid);
			}
		} catch (HibernateException e) {
			System.out.println("Unable to get Next Broadcast ID");
			logger.error(e);
		}
    	return "Error";
    }
    
    /**
     * Gets the broadcast.
     *
     * @param broadcast_id the broadcast_id
     * @return the broadcast
     */
    public Broadcast getBroadcast(String broadcast_id) {
        Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return (Broadcast)crit.uniqueResult();
    }
    
    /**
     * Gets the broadcast by id.
     *
     * @param id the id
     * @return the broadcast by id
     */
    public Broadcast getBroadcastById(int id) {
        Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("id", id));
        return (Broadcast)crit.uniqueResult();
    }
    
    /**
     * Gets the broadcasts by campaign id.
     *
     * @param campaign_id the campaign_id
     * @return the broadcasts by campaign id
     */
    public List<Broadcast> getBroadcastsByCampaignId (String campaign_id) {
        Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("campaign_id", campaign_id));
        return crit.list();
    }
    
    /**
     * Gets defined broadcasts by campaign id.
     *
     * @param campaign_id the campaign_id
     * @return the broadcasts by campaign id
     */
    public List<Broadcast> getDefinedBroadcastsByCampaignId (String campaign_id) {
        Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("campaign_id", campaign_id));
        crit.add(Restrictions.eq("status", "DEFINED"));
        return crit.list();
    }
    
    /**
     * Save or update.
     *
     * @param broadcast the broadcast
     * @return the string
     */
    public String saveOrUpdate(Broadcast broadcast) {
	session().saveOrUpdate(broadcast);
        return String.valueOf(broadcast.getBroadcast_id());
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(int id) {
        Query query = session().createQuery("delete Broadcast b where b.id=:id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
    
    
    /**
     * isBroadcastExist
     * 
     *  @param broadcast_id the broadcast_id
     *  @return true, if broadcast_id exists
     */
    public boolean isBroadcastExist(String broadcast_id) {
    	Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        Broadcast broadcast = (Broadcast)crit.uniqueResult();
    	return broadcast != null;
    }
}
