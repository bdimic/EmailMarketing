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

/**
 *
 * @author bdimic
 */
@Service("broadcastTemplateService")
public class BroadcastTemplateService {
    private BroadcastTemplateDao broadcastTemplateDao;

    @Autowired
    public void setBroadcastTemplateDao(BroadcastTemplateDao broadcastTemplateDao) {
        this.broadcastTemplateDao = broadcastTemplateDao;
    }
    
    public List<BroadcastTemplate> getAllBroadcasts() {
        List<BroadcastTemplate> broadcastTemplate = broadcastTemplateDao.getAllBroadcastTemplates();
        return broadcastTemplate;
    }
    
    public BroadcastTemplate getBroadcastTemplate(String id) {
        BroadcastTemplate broadcastTemplate = broadcastTemplateDao.getBroadcastTemplate(id);
        return broadcastTemplate;
    }
    
    public String SaveOrUpdate(BroadcastTemplate broadcastTemplate) {
        return broadcastTemplateDao.saveOrUpdate(broadcastTemplate);
    }
    
    public boolean delete(int id) {
        return broadcastTemplateDao.delete(id);
    }
}
