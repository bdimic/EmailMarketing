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

/**
 *
 * @author bdimic
 */
@Service("broadcastService")
public class BroadcastService {
    private BroadcastDao broadcastDao;

    @Autowired
    public void setBroadcastDao(BroadcastDao broadcastDao) {
        this.broadcastDao = broadcastDao;
    }
    
    public String getNextBroadcastId() {
    	return broadcastDao.getNextBroadcastId();
    }
    
    public List<Broadcast> getAllBroadcasts() {
        List<Broadcast> broadcasts = broadcastDao.getAllBroadcasts();
        return broadcasts;
    }
    
    public Broadcast getBroadcast(String broadcast_id) {
        Broadcast broadcast = broadcastDao.getBroadcast(broadcast_id);
        return broadcast;
    }
    
    public Broadcast getBroadcastById(int id) {
        Broadcast broadcast = broadcastDao.getBroadcastById(id);
        return broadcast;
    }
    
    public List<Broadcast> getBroadcastsByCampaignId(String campaign_id) {
        List<Broadcast> broadcasts = broadcastDao.getBroadcastsByCampaignId(campaign_id);
        return broadcasts;
    }
    
    public String SaveOrUpdate(Broadcast broadcast) {
        return broadcastDao.saveOrUpdate(broadcast);
    }
    
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
    
    public boolean delete(int id) {
        return broadcastDao.delete(id);
    }
}
