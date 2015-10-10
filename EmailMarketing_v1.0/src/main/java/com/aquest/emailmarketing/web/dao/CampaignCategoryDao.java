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

/**
 *
 * @author bdimic
 */
@Repository
@Transactional
@Component("campaignCategoryDao")
public class CampaignCategoryDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public List<CampaignCategory> getCategories() {
        Criteria crit = session().createCriteria(CampaignCategory.class);
        return crit.list();
    }
    
    public CampaignCategory getCategoryById(int category_id) {
    	Criteria crit = session().createCriteria(CampaignCategory.class);
    	crit.add(Restrictions.eq("category_id", category_id));
    	
    	return (CampaignCategory)crit.uniqueResult();
    }
    
    public CampaignCategory getCategory(String category_id) {
        Criteria crit = session().createCriteria(CampaignCategory.class);
        int cat_id = Integer.parseInt(category_id);
        crit.add(Restrictions.eq("category_id", cat_id));
        
        return (CampaignCategory)crit.uniqueResult();
    }
    
    public String saveOrUpdate(CampaignCategory campaignCategory) {
    	session().saveOrUpdate(campaignCategory);
            return String.valueOf(campaignCategory.getCategory_id());
    }
    
    public boolean delete(String category_id) {
    	int cat_id = Integer.parseInt(category_id);
        Query query = session().createQuery("delete CampaignCategory c where c.category_id=:id");
        query.setParameter("id", cat_id);
        return query.executeUpdate() == 1;
    }
}
