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

/**
 *
 * @author bdimic
 */
@Service("trackingConfigService")
public class TrackingConfigService {
    private TrackingConfigDao trackingConfigDao;

    @Autowired
    public void setTrackingConfigDao(TrackingConfigDao trackingConfigDao) {
        this.trackingConfigDao = trackingConfigDao;
    }
    
    public List<TrackingConfig> getAllTrackingConfig() {
        List<TrackingConfig> trackingConfigs = trackingConfigDao.getAllTrackingConfig();
        return trackingConfigs;
    }
    
    public TrackingConfig getTrackingConfig(String broadcast_id) {
    	TrackingConfig trackingConfig = trackingConfigDao.getTrackingConfig(broadcast_id);
        return trackingConfig;
    }
    
    public TrackingConfig getConfigById(int id) {
    	TrackingConfig trackingConfig = trackingConfigDao.getConfigById(id);
        return trackingConfig;
    }
        
    public String SaveOrUpdate(TrackingConfig trackingConfig) {
        return trackingConfigDao.saveOrUpdate(trackingConfig);
    }
        
    public void delete(String id) {
    	trackingConfigDao.delete(id);
    }
}
