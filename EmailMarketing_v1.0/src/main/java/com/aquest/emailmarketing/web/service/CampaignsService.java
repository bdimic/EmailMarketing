/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.Campaigns;
import com.aquest.emailmarketing.web.dao.CampaignsDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bdimic
 */
@Service("campaignsService")
public class CampaignsService {
    private CampaignsDao campaignsDao;
    
    @Autowired
    public void setCampaignsDao(CampaignsDao campaignsDao) {
        this.campaignsDao = campaignsDao;
    }
    
    public String getNextCampaignId() {
    	return campaignsDao.getNextCampaignId();
    }
    
    public List<Campaigns> getCampaigns() {
        System.out.println("Ovo se pokrenulo");
        List<Campaigns> campaigns = campaignsDao.getCampaigns();
        System.out.println(campaigns.toString());
        return campaigns;
    }
    
    public Campaigns getCampaign(String campaign_id) {
        Campaigns campaign = campaignsDao.getCampaign(campaign_id);
        return campaign;
    }
    
    public String SaveOrUpdate(Campaigns campaign) {
        return campaignsDao.saveOrUpdate(campaign);
    }
    
    public boolean delete(String campaign_id) {
        boolean isDeleted = campaignsDao.delete(campaign_id);
        return isDeleted;
    }
}
