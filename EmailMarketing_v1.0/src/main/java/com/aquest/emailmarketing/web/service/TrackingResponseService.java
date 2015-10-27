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
import com.aquest.emailmarketing.web.dao.TrackingResponse;
import com.aquest.emailmarketing.web.dao.TrackingResponseDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bdimic
 */
@Service("trackingResponseService")
public class TrackingResponseService {
    private TrackingResponseDao trackingResponseDao;

    @Autowired
    public void setTrackingResponseDao(TrackingResponseDao trackingResponseDao) {
        this.trackingResponseDao = trackingResponseDao;
    }
    
    public List<TrackingResponse> getAllTrackingResponse() {
        List<TrackingResponse> trackingResponses = trackingResponseDao.getAllTrackingResponse();
        return trackingResponses;
    }
    
    public List<TrackingResponse> getTrackingResponseByBroadcastId(String broadcast_id) {
    	List<TrackingResponse> trackingResponse = trackingResponseDao.getTrackingResponseByBroadcastId(broadcast_id);
        return trackingResponse;
    }
    
    public List<TrackingResponse> getTrackingResponseByEmail(String email) {
    	List<TrackingResponse> trackingResponse = trackingResponseDao.getTrackingResponseByBroadcastId(email);
        return trackingResponse;
    }
    
    public TrackingResponse getResponseById(int id) {
    	TrackingResponse trackingResponse = trackingResponseDao.getResponseById(id);
        return trackingResponse;
    }
    
    public List<TrackingResponse> checkResponseExists(String unique_id, String broadcast_id, String response_type, String response_source, String response_url, String response_time) {
    	List<TrackingResponse> trackingResponse = trackingResponseDao.checkResponseExists(unique_id, broadcast_id, response_type, response_source, response_url, response_time);
    	return trackingResponse;
    }	
    	
    public String SaveOrUpdate(TrackingResponse trackingResponse) {
        return trackingResponseDao.saveOrUpdate(trackingResponse);
    }
        
    public void delete(String id) {
    	trackingResponseDao.delete(id);
    }
}
