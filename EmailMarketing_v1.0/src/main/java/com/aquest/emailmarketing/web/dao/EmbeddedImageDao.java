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

/**
 *
 * @author bdimic
 */
@Repository
@Transactional
@Component("embeddedImageDao")
public class EmbeddedImageDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Session session() {
		return sessionFactory.getCurrentSession();
    }
    
    public EmbeddedImage getEmbeddedImages(String broadcast_id) {
        Criteria crit = session().createCriteria(EmbeddedImage.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return (EmbeddedImage)crit.uniqueResult();
    }
    
    public boolean exists(String broadcast_id) {
		Criteria crit = session().createCriteria(EmbeddedImage.class);
		crit.add(Restrictions.eq("broadcast_id", broadcast_id));
		EmbeddedImage embeddedImage = (EmbeddedImage)crit.uniqueResult();
		return embeddedImage != null;
	}
    
    public String saveOrUpdate(EmbeddedImage embeddedImage) {
    	session().saveOrUpdate(embeddedImage);
            return String.valueOf(embeddedImage.getBroadcast_id());
    }
    
    public boolean delete(String broadcast_id) {
        Query query = session().createQuery("delete EmbeddedImage e where e.broadcast_id=:broadcast_id");
        query.setParameter("broadcast_id", broadcast_id);
        return query.executeUpdate() == 1;
    }
}
