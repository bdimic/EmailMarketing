/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.Config;
import com.aquest.emailmarketing.web.dao.ConfigDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigService.
 */

@Service("configService")
public class ConfigService {
    
    /** The config dao. */
    private ConfigDao configDao;

    /**
     * Sets the config dao.
     *
     * @param configDao the new config dao
     */
    @Autowired
    public void setConfigDao(ConfigDao configDao) {
        this.configDao = configDao;
    }
    
    public List<Config> getAllConfigs() {
        List<Config> allConfigs = configDao.getAllConfigs();
        return allConfigs;
    }
    
    /**
     * Gets the config.
     *
     * @return the config
     */
    public Config getConfig(String key) {
    	Config config = configDao.getConfig(key);
        return config;
    }
        
    /**
     * Save or update.
     *
     * @param config the config
     * @return the string
     */
    public String saveOrUpdate(Config config) {
        return configDao.saveOrUpdate(config);
    }
}
