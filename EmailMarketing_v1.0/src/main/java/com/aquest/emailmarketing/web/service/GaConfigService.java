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

/**
 *
 * @author bdimic
 */

@Service("gaConfigService")
public class GaConfigService {
    private GaConfigDao gaConfigDao;

    @Autowired
    public void setGaConfigDao(GaConfigDao gaConfigDao) {
        this.gaConfigDao = gaConfigDao;
    }
    
    public GaConfig getGaConfig() {
    	GaConfig gaConfig = gaConfigDao.getGaConfig();
        return gaConfig;
    }
        
    public String saveOrUpdate(GaConfig gaConfig) {
        return gaConfigDao.saveOrUpdate(gaConfig);
    }
}
