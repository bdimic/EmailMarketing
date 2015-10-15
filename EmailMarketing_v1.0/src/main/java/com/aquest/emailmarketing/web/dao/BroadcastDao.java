/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.util.Date;
import java.util.List;

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

/**
 *
 * @author bdimic
 */
@Repository
@Transactional
@Component("broadcastDao")
public class BroadcastDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<Broadcast> getAllBroadcasts() {
        Criteria crit = session().createCriteria(Broadcast.class);
        return crit.list();
    }
    
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
			// TODO Auto-generated catch block
			System.out.println("Unable to get Next Broadcast ID");
			e.printStackTrace();
		}
    	return "Error";
    }
    
    public Broadcast getBroadcast(String broadcast_id) {
        Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return (Broadcast)crit.uniqueResult();
    }
    
    public Broadcast getBroadcastById(int id) {
        Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("id", id));
        return (Broadcast)crit.uniqueResult();
    }
    
    public List<Broadcast> getBroadcastsByCampaignId (String campaign_id) {
        Criteria crit = session().createCriteria(Broadcast.class);
        crit.add(Restrictions.eq("campaign_id", campaign_id));
        return crit.list();
    }
    
    public String saveOrUpdate(Broadcast broadcast) {
	session().saveOrUpdate(broadcast);
        return String.valueOf(broadcast.getBroadcast_id());
    }
    
    public boolean delete(int id) {
        Query query = session().createQuery("delete Broadcast b where b.id=:id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
