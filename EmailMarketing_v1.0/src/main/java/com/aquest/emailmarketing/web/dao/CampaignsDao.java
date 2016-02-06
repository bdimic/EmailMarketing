/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aquest.emailmarketing.web.dao;

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

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignsDao.
 */
@Repository
@Transactional
@Component("campaignsDao")
public class CampaignsDao {
    
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
     * Gets the campaigns.
     *
     * @return the campaigns
     */
    public List<Campaigns> getCampaigns() {
        Criteria crit = session().createCriteria(Campaigns.class);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //Date date = new Date();
        //crit.add(Restrictions.ge("comm_start_dttm", date));
        //crit.add(Restrictions.le("comm_end_dttm", date));
        System.out.println("Dao se pokrenuo");
        System.out.println(crit.list().toString());
        return crit.list();
    }
    
    /**
     * Gets the next campaign id.
     *
     * @return the next campaign id
     */
    public String getNextCampaignId() {
    	int campaignid = 1000000;
    	try {
			Query query = session().createQuery("select max(cast(substring(c.campaign_id, 3) AS int)) from Campaigns c where c.campaign_source='EM'");
			List currentSeq = query.list();
			if(currentSeq.get(0) == null) {
				return "EM"+String.valueOf(campaignid);
			} else {
				campaignid = (Integer)currentSeq.get(0)+1;
				return "EM"+String.valueOf(campaignid);
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to get Next Campaign ID");
			e.printStackTrace();
		}
    	return "Error";
    }
    
    /**
     * Gets the campaign.
     *
     * @param campaign_id the campaign_id
     * @return the campaign
     */
    public Campaigns getCampaign(String campaign_id) {
        Criteria crit = session().createCriteria(Campaigns.class);
        crit.add(Restrictions.eq("campaign_id", campaign_id));
        
        return (Campaigns)crit.uniqueResult();
    }
    
    /**
     * Save or update.
     *
     * @param campaigns the campaigns
     * @return the string
     */
    public String saveOrUpdate(Campaigns campaigns) {
    	session().saveOrUpdate(campaigns);
            return String.valueOf(campaigns.getCampaign_id());
    }
    
    /**
     * Delete.
     *
     * @param campaign_id the campaign_id
     * @return true, if successful
     */
    public boolean delete(String campaign_id) {
        Query query = session().createQuery("delete Campaigns c where c.campaign_id=:id");
        query.setParameter("id", campaign_id);
        return query.executeUpdate() == 1;
    }
}
