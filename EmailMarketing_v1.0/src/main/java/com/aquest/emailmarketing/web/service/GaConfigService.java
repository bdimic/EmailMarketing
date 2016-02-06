/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.GaConfig;
import com.aquest.emailmarketing.web.dao.GaConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class GaConfigService.
 */

@Service("gaConfigService")
public class GaConfigService {
    
    /** The ga config dao. */
    private GaConfigDao gaConfigDao;

    /**
     * Sets the ga config dao.
     *
     * @param gaConfigDao the new ga config dao
     */
    @Autowired
    public void setGaConfigDao(GaConfigDao gaConfigDao) {
        this.gaConfigDao = gaConfigDao;
    }
    
    /**
     * Gets the ga config.
     *
     * @return the ga config
     */
    public GaConfig getGaConfig() {
    	GaConfig gaConfig = gaConfigDao.getGaConfig();
        return gaConfig;
    }
        
    /**
     * Save or update.
     *
     * @param gaConfig the ga config
     * @return the string
     */
    public String saveOrUpdate(GaConfig gaConfig) {
        return gaConfigDao.saveOrUpdate(gaConfig);
    }
}
