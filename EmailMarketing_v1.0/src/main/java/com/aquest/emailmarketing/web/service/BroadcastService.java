/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.BroadcastDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Auto-generated Javadoc
/**
 * The Class BroadcastService.
 */
@Service("broadcastService")
public class BroadcastService {
    
    /** The broadcast dao. */
    private BroadcastDao broadcastDao;

    /**
     * Sets the broadcast dao.
     *
     * @param broadcastDao the new broadcast dao
     */
    @Autowired
    public void setBroadcastDao(BroadcastDao broadcastDao) {
        this.broadcastDao = broadcastDao;
    }
    
    /**
     * Gets the next broadcast id.
     *
     * @return the next broadcast id
     */
    public String getNextBroadcastId() {
    	return broadcastDao.getNextBroadcastId();
    }
    
    /**
     * Gets the all broadcasts.
     *
     * @return the all broadcasts
     */
    public List<Broadcast> getAllBroadcasts() {
        List<Broadcast> broadcasts = broadcastDao.getAllBroadcasts();
        return broadcasts;
    }
    
    /**
     * Gets the broadcast.
     *
     * @param broadcast_id the broadcast_id
     * @return the broadcast
     */
    public Broadcast getBroadcast(String broadcast_id) {
        Broadcast broadcast = broadcastDao.getBroadcast(broadcast_id);
        return broadcast;
    }
    
    /**
     * Gets the broadcast by id.
     *
     * @param id the id
     * @return the broadcast by id
     */
    public Broadcast getBroadcastById(int id) {
        Broadcast broadcast = broadcastDao.getBroadcastById(id);
        return broadcast;
    }
    
    /**
     * Gets the broadcasts by campaign id.
     *
     * @param campaign_id the campaign_id
     * @return the broadcasts by campaign id
     */
    public List<Broadcast> getBroadcastsByCampaignId(String campaign_id) {
        List<Broadcast> broadcasts = broadcastDao.getBroadcastsByCampaignId(campaign_id);
        return broadcasts;
    }
    
    /**
     * Save or update.
     *
     * @param broadcast the broadcast
     * @return the string
     */
    public String SaveOrUpdate(Broadcast broadcast) {
        return broadcastDao.saveOrUpdate(broadcast);
    }
    
    /**
     * Checks for broadcast.
     *
     * @param campaign_id the campaign_id
     * @return true, if successful
     */
    public boolean hasBroadcast(String campaign_id) {

            if (campaign_id == null) {
                    return false;
            }

            List<Broadcast> broadcasts = broadcastDao.getBroadcastsByCampaignId(campaign_id);

            if (broadcasts.size() == 0) {
                    return false;
            }

            return true;
    }
    
    /**
     * Delete.
     *
     * @param id the id
     * @return true, if successful
     */
    public boolean delete(int id) {
        return broadcastDao.delete(id);
    }
}
