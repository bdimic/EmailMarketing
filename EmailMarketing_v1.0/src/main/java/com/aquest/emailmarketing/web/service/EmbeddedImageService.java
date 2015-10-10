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

/**
 *
 * @author bdimic
 */
@Service("embeddedImageService")
public class EmbeddedImageService {
    private EmbeddedImageDao embeddedImageDao;

    @Autowired
    public void setEmbeddedImageDao(EmbeddedImageDao embeddedImageDao) {
        this.embeddedImageDao = embeddedImageDao;
    }
    
    public EmbeddedImage getEmbeddedImages(String broadcast_id) {
        EmbeddedImage embeddedImage = embeddedImageDao.getEmbeddedImages(broadcast_id);
        return embeddedImage;
    }
    
    public String SaveOrUpdate(EmbeddedImage embeddedImage) {
        return embeddedImageDao.saveOrUpdate(embeddedImage);
    }
    
    public boolean exists(String broadcast_id) {
		return embeddedImageDao.exists(broadcast_id);
	}
    
    public void delete(String broadcast_id) {
    	embeddedImageDao.delete(broadcast_id);
    }
}
