/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.BroadcastDao;
import com.aquest.emailmarketing.web.dao.TrackingConfig;
import com.aquest.emailmarketing.web.dao.TrackingConfigDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class TrackingConfigService.
 */
@Service("trackingConfigService")
public class TrackingConfigService {
    
    /** The tracking config dao. */
    private TrackingConfigDao trackingConfigDao;

    /**
     * Sets the tracking config dao.
     *
     * @param trackingConfigDao the new tracking config dao
     */
    @Autowired
    public void setTrackingConfigDao(TrackingConfigDao trackingConfigDao) {
        this.trackingConfigDao = trackingConfigDao;
    }
    
    /**
     * Gets the all tracking config.
     *
     * @return the all tracking config
     */
    public List<TrackingConfig> getAllTrackingConfig() {
        List<TrackingConfig> trackingConfigs = trackingConfigDao.getAllTrackingConfig();
        return trackingConfigs;
    }
    
    /**
     * Gets the tracking config.
     *
     * @param broadcast_id the broadcast_id
     * @return the tracking config
     */
    public TrackingConfig getTrackingConfig(String broadcast_id) {
    	TrackingConfig trackingConfig = trackingConfigDao.getTrackingConfig(broadcast_id);
        return trackingConfig;
    }
    
    /**
     * Gets the config by id.
     *
     * @param id the id
     * @return the config by id
     */
    public TrackingConfig getConfigById(int id) {
    	TrackingConfig trackingConfig = trackingConfigDao.getConfigById(id);
        return trackingConfig;
    }
        
    /**
     * Save or update.
     *
     * @param trackingConfig the tracking config
     * @return the string
     */
    public String SaveOrUpdate(TrackingConfig trackingConfig) {
        return trackingConfigDao.saveOrUpdate(trackingConfig);
    }
        
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(String id) {
    	return trackingConfigDao.delete(id);
    }
}
