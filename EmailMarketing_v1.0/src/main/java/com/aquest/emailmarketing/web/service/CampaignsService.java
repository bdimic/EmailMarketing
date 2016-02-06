/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.Campaigns;
import com.aquest.emailmarketing.web.dao.CampaignsDao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignsService.
 */
@Service("campaignsService")
public class CampaignsService {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(com.aquest.emailmarketing.web.service.CampaignsService.class);
	
    /** The campaigns dao. */
    private CampaignsDao campaignsDao;
    
    /**
     * Sets the campaigns dao.
     *
     * @param campaignsDao the new campaigns dao
     */
    @Autowired
    public void setCampaignsDao(CampaignsDao campaignsDao) {
        this.campaignsDao = campaignsDao;
    }
    
    /**
     * Gets the next campaign id.
     *
     * @return the next campaign id
     */
    public String getNextCampaignId() {
    	return campaignsDao.getNextCampaignId();
    }
    
    /**
     * Gets the campaigns.
     *
     * @return the campaigns
     */
    public List<Campaigns> getCampaigns() {
        logger.debug("Ovo se pokrenulo");;
        List<Campaigns> campaigns = campaignsDao.getCampaigns();
        logger.debug(campaigns.toString());
        return campaigns;
    }
    
    /**
     * Gets the campaign.
     *
     * @param campaign_id the campaign_id
     * @return the campaign
     */
    public Campaigns getCampaign(String campaign_id) {
        Campaigns campaign = campaignsDao.getCampaign(campaign_id);
        return campaign;
    }
    
    /**
     * Save or update.
     *
     * @param campaign the campaign
     * @return the string
     */
    public String SaveOrUpdate(Campaigns campaign) {
        return campaignsDao.saveOrUpdate(campaign);
    }
    
    /**
     * Delete.
     *
     * @param campaign_id the campaign_id
     * @return true, if successful
     */
    public boolean delete(String campaign_id) {
        boolean isDeleted = campaignsDao.delete(campaign_id);
        return isDeleted;
    }
}
