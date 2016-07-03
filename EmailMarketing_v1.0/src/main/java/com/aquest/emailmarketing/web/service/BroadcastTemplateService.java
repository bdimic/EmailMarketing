/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.BroadcastTemplate;
import com.aquest.emailmarketing.web.dao.BroadcastTemplateDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class BroadcastTemplateService.
 */
@Service("broadcastTemplateService")
public class BroadcastTemplateService {
    
    /** The broadcast template dao. */
    private BroadcastTemplateDao broadcastTemplateDao;

    /**
     * Sets the broadcast template dao.
     *
     * @param broadcastTemplateDao the new broadcast template dao
     */
    @Autowired
    public void setBroadcastTemplateDao(BroadcastTemplateDao broadcastTemplateDao) {
        this.broadcastTemplateDao = broadcastTemplateDao;
    }
    
    /**
     * Gets the all broadcasts.
     *
     * @return the all broadcasts
     */
    public List<BroadcastTemplate> getAllBroadcasts() {
        List<BroadcastTemplate> broadcastTemplate = broadcastTemplateDao.getAllBroadcastTemplates();
        return broadcastTemplate;
    }
    
    /**
     * Gets the defined broadcasts.
     *
     * @return the defined broadcasts
     */
    public List<BroadcastTemplate> getDefinedBroadcasts() {
        List<BroadcastTemplate> broadcastTemplate = broadcastTemplateDao.getDefinedBroadcastTemplates();
        return broadcastTemplate;
    }
    
    /**
     * Gets the broadcast template.
     *
     * @param id the id
     * @return the broadcast template
     */
    public BroadcastTemplate getBroadcastTemplate(String id) {
        BroadcastTemplate broadcastTemplate = broadcastTemplateDao.getBroadcastTemplate(id);
        return broadcastTemplate;
    }
    
    /**
     * Gets the broadcast template by id.
     *
     * @param id the id
     * @return the broadcast template by id
     */
    public BroadcastTemplate getBroadcastTemplateById(int id) {
        BroadcastTemplate broadcastTemplate = broadcastTemplateDao.getBroadcastTemplateById(id);
        return broadcastTemplate;
    }
    
    /**
     * Save or update.
     *
     * @param broadcastTemplate the broadcast template
     * @return the string
     */
    public String SaveOrUpdate(BroadcastTemplate broadcastTemplate) {
        return broadcastTemplateDao.saveOrUpdate(broadcastTemplate);
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(int id) {
        return broadcastTemplateDao.delete(id);
    }
}
