/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.CampaignCategory;
import com.aquest.emailmarketing.web.dao.CampaignCategoryDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bdimic
 */
@Service("campaignCategoryService")
public class CampaignCategoryService {
    private CampaignCategoryDao campaignCategoryDao;
    
    @Autowired
    public void setCampaignCategoryDao(CampaignCategoryDao campaignCategoryDao) {
		this.campaignCategoryDao = campaignCategoryDao;
	}
    
    public List<CampaignCategory> getCategories() {
        System.out.println("Ovo se pokrenulo");
        List<CampaignCategory> campaignCategory = campaignCategoryDao.getCategories();
        return campaignCategory;
    }
    
    public CampaignCategory getCategoryById(int category_id) {
    	CampaignCategory campaignCategory = campaignCategoryDao.getCategoryById(category_id);
        return campaignCategory;
    }

	public CampaignCategory getCategory(String category_id) {
		CampaignCategory campaignCategory = campaignCategoryDao.getCategory(category_id);
        return campaignCategory;
    }
    
    public String SaveOrUpdate(CampaignCategory campaignCategory) {
        return campaignCategoryDao.saveOrUpdate(campaignCategory);
    }
    
    public boolean delete(String category_id) {
        boolean isDeleted = campaignCategoryDao.delete(category_id);
        return isDeleted;
    }
}
