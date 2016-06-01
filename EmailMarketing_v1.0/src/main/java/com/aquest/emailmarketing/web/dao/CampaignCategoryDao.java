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

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignCategoryDao.
 */
@Repository
@Transactional
@Component("campaignCategoryDao")
public class CampaignCategoryDao {
    
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
     * Gets the categories.
     *
     * @return the categories
     */
    public List<CampaignCategory> getCategories() {
        Criteria crit = session().createCriteria(CampaignCategory.class);
        return crit.list();
    }
    
    /**
     * Gets the category by id.
     *
     * @param category_id the category_id
     * @return the category by id
     */
    public CampaignCategory getCategoryById(int category_id) {
    	Criteria crit = session().createCriteria(CampaignCategory.class);
    	crit.add(Restrictions.eq("category_id", category_id));
    	
    	return (CampaignCategory)crit.uniqueResult();
    }
    
    /**
     * Gets the category.
     *
     * @param category_id the category_id
     * @return the category
     */
    public CampaignCategory getCategory(int category_id) {
        Criteria crit = session().createCriteria(CampaignCategory.class);
        crit.add(Restrictions.eq("category_id", category_id));
        
        return (CampaignCategory)crit.uniqueResult();
    }
    
    /**
     * Save or update.
     *
     * @param campaignCategory the campaign category
     * @return the string
     */
    public String saveOrUpdate(CampaignCategory campaignCategory) {
    	session().saveOrUpdate(campaignCategory);
            return String.valueOf(campaignCategory.getCategory_id());
    }
    
    /**
     * Delete.
     *
     * @param category_id the category_id
     * @return true, if successful
     */
    public boolean delete(int category_id) {
        Query query = session().createQuery("delete CampaignCategory c where c.category_id=:id");
        query.setParameter("id", category_id);
        return query.executeUpdate() == 1;
    }
}
