/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.EmbeddedImage;
import com.aquest.emailmarketing.web.dao.EmbeddedImageDao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class EmbeddedImageService.
 */
@Service("embeddedImageService")
public class EmbeddedImageService {
    
    /** The embedded image dao. */
    private EmbeddedImageDao embeddedImageDao;

    /**
     * Sets the embedded image dao.
     *
     * @param embeddedImageDao the new embedded image dao
     */
    @Autowired
    public void setEmbeddedImageDao(EmbeddedImageDao embeddedImageDao) {
        this.embeddedImageDao = embeddedImageDao;
    }
    
    /**
     * Gets the embedded images.
     *
     * @param broadcast_id the broadcast_id
     * @return the embedded images
     */
    public EmbeddedImage getEmbeddedImages(String broadcast_id) {
        EmbeddedImage embeddedImage = embeddedImageDao.getEmbeddedImages(broadcast_id);
        return embeddedImage;
    }
    
    public EmbeddedImage getEmbeddedImagesFromTemplateId(int bcast_template_id) {
    	EmbeddedImage embeddedImage = embeddedImageDao.getEmbeddedImagesFromTemplateId(bcast_template_id);
    	return embeddedImage;
    }
    
    /**
     * Save or update.
     *
     * @param embeddedImage the embedded image
     * @return the string
     */
    public String SaveOrUpdate(EmbeddedImage embeddedImage) {
        return embeddedImageDao.saveOrUpdate(embeddedImage);
    }
    
    /**
     * Exists.
     *
     * @param broadcast_id the broadcast_id
     * @return true, if successful
     */
    public boolean exists(String broadcast_id) {
		return embeddedImageDao.exists(broadcast_id);
	}
    
    /**
     * Delete.
     *
     * @param broadcast_id the broadcast_id
     */
    public void delete(String broadcast_id) {
    	embeddedImageDao.delete(broadcast_id);
    }
}
