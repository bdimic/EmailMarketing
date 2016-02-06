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

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class TrackingResponseService.
 */
@Service("trackingResponseService")
public class TrackingResponseService {
    
    /** The tracking response dao. */
    private TrackingResponseDao trackingResponseDao;

    /**
     * Sets the tracking response dao.
     *
     * @param trackingResponseDao the new tracking response dao
     */
    @Autowired
    public void setTrackingResponseDao(TrackingResponseDao trackingResponseDao) {
        this.trackingResponseDao = trackingResponseDao;
    }
    
    /**
     * Gets the all tracking response.
     *
     * @return the all tracking response
     */
    public List<TrackingResponse> getAllTrackingResponse() {
        List<TrackingResponse> trackingResponses = trackingResponseDao.getAllTrackingResponse();
        return trackingResponses;
    }
    
    /**
     * Gets the tracking response by broadcast id.
     *
     * @param broadcast_id the broadcast_id
     * @return the tracking response by broadcast id
     */
    public List<TrackingResponse> getTrackingResponseByBroadcastId(String broadcast_id) {
    	List<TrackingResponse> trackingResponse = trackingResponseDao.getTrackingResponseByBroadcastId(broadcast_id);
        return trackingResponse;
    }
    
    /**
     * Gets the tracking response by email.
     *
     * @param email the email
     * @return the tracking response by email
     */
    public List<TrackingResponse> getTrackingResponseByEmail(String email) {
    	List<TrackingResponse> trackingResponse = trackingResponseDao.getTrackingResponseByBroadcastId(email);
        return trackingResponse;
    }
    
    /**
     * Gets the response by id.
     *
     * @param id the id
     * @return the response by id
     */
    public TrackingResponse getResponseById(int id) {
    	TrackingResponse trackingResponse = trackingResponseDao.getResponseById(id);
        return trackingResponse;
    }
    
    /**
     * Check response exists.
     *
     * @param unique_id the unique_id
     * @param broadcast_id the broadcast_id
     * @param response_type the response_type
     * @param response_source the response_source
     * @param response_url the response_url
     * @param response_time the response_time
     * @return the list
     */
    public List<TrackingResponse> checkResponseExists(String unique_id, String broadcast_id, String response_type, String response_source, String response_url, Timestamp response_time) {
    	List<TrackingResponse> trackingResponse = trackingResponseDao.checkResponseExists(unique_id, broadcast_id, response_type, response_source, response_url, response_time);
    	return trackingResponse;
    }
    
    /**
     * Gets the no of opens by broadcast.
     *
     * @param broadcast_id the broadcast_id
     * @return the no of opens by broadcast
     */
    public int getNoOfOpensByBroadcast(String broadcast_id) {
    	return trackingResponseDao.getNoOfOpensByBroadcast(broadcast_id);
    }
    
    /**
     * Gets the no of click by broadcast.
     *
     * @param broadcast_id the broadcast_id
     * @return the no of click by broadcast
     */
    public int getNoOfClickByBroadcast(String broadcast_id) {
    	return trackingResponseDao.getNoOfClickByBroadcast(broadcast_id);
    }
    	
    /**
     * Save or update.
     *
     * @param trackingResponse the tracking response
     * @return the string
     */
    public String SaveOrUpdate(TrackingResponse trackingResponse) {
        return trackingResponseDao.saveOrUpdate(trackingResponse);
    }
        
    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(String id) {
    	trackingResponseDao.delete(id);
    }
}
