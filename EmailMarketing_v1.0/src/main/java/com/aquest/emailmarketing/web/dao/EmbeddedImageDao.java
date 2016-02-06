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
 * The Class EmbeddedImageDao.
 */
@Repository
@Transactional
@Component("embeddedImageDao")
public class EmbeddedImageDao {
    
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
     * Gets the embedded images.
     *
     * @param broadcast_id the broadcast_id
     * @return the embedded images
     */
    public EmbeddedImage getEmbeddedImages(String broadcast_id) {
        Criteria crit = session().createCriteria(EmbeddedImage.class);
        crit.add(Restrictions.eq("broadcast_id", broadcast_id));
        return (EmbeddedImage)crit.uniqueResult();
    }
    
    public EmbeddedImage getEmbeddedImagesFromTemplateId(int bcast_template_id) {
        Criteria crit = session().createCriteria(EmbeddedImage.class);
        crit.add(Restrictions.eq("bcast_template_id", bcast_template_id));
        return (EmbeddedImage)crit.uniqueResult();
    }
    
    /**
     * Exists.
     *
     * @param broadcast_id the broadcast_id
     * @return true, if successful
     */
    public boolean exists(String broadcast_id) {
		Criteria crit = session().createCriteria(EmbeddedImage.class);
		crit.add(Restrictions.eq("broadcast_id", broadcast_id));
		EmbeddedImage embeddedImage = (EmbeddedImage)crit.uniqueResult();
		return embeddedImage != null;
	}
    
    /**
     * Save or update.
     *
     * @param embeddedImage the embedded image
     * @return the string
     */
    public String saveOrUpdate(EmbeddedImage embeddedImage) {
    	session().saveOrUpdate(embeddedImage);
            return String.valueOf(embeddedImage.getBroadcast_id());
    }
    
    /**
     * Delete.
     *
     * @param broadcast_id the broadcast_id
     * @return true, if successful
     */
    public boolean delete(String broadcast_id) {
        Query query = session().createQuery("delete EmbeddedImage e where e.broadcast_id=:broadcast_id");
        query.setParameter("broadcast_id", broadcast_id);
        return query.executeUpdate() == 1;
    }
}
