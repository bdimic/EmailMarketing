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

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignCategoryService.
 */
@Service("campaignCategoryService")
public class CampaignCategoryService {
    
    /** The campaign category dao. */
    private CampaignCategoryDao campaignCategoryDao;
    
    /**
     * Sets the campaign category dao.
     *
     * @param campaignCategoryDao the new campaign category dao
     */
    @Autowired
    public void setCampaignCategoryDao(CampaignCategoryDao campaignCategoryDao) {
		this.campaignCategoryDao = campaignCategoryDao;
	}
    
    /**
     * Gets the categories.
     *
     * @return the categories
     */
    public List<CampaignCategory> getCategories() {
        System.out.println("Ovo se pokrenulo");
        List<CampaignCategory> campaignCategory = campaignCategoryDao.getCategories();
        return campaignCategory;
    }
    
    /**
     * Gets the category by id.
     *
     * @param category_id the category_id
     * @return the category by id
     */
    public CampaignCategory getCategoryById(int category_id) {
    	CampaignCategory campaignCategory = campaignCategoryDao.getCategoryById(category_id);
        return campaignCategory;
    }

	/**
	 * Gets the category.
	 *
	 * @param category_id the category_id
	 * @return the category
	 */
	public CampaignCategory getCategory(int category_id) {
		CampaignCategory campaignCategory = campaignCategoryDao.getCategory(category_id);
        return campaignCategory;
    }
    
    /**
     * Save or update.
     *
     * @param campaignCategory the campaign category
     * @return the string
     */
    public String SaveOrUpdate(CampaignCategory campaignCategory) {
        return campaignCategoryDao.saveOrUpdate(campaignCategory);
    }
    
    /**
     * Delete.
     *
     * @param category_id the category_id
     * @return true, if successful
     */
    public boolean delete(int category_id) {
        boolean isDeleted = campaignCategoryDao.delete(category_id);
        return isDeleted;
    }
}
