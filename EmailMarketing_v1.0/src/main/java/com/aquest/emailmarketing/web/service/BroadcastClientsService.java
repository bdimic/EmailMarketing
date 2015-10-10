/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.BroadcastClients;
import com.aquest.emailmarketing.web.dao.BroadcastClientsDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bdimic
 */
@Service("broadcastClientsService")
public class BroadcastClientsService {
    private BroadcastClientsDao broadcastClientsDao;

    @Autowired
    public void setBroadcastClientsDao(BroadcastClientsDao broadcastClientsDao) {
        this.broadcastClientsDao = broadcastClientsDao;
    }
    
    public void SaveOrUpdate(BroadcastClients broadcastClients) {
        broadcastClientsDao.saveOrUpdate(broadcastClients);
    }
    
    public List<BroadcastClients> getBroadcastClients(String broadcast_id) {
        List<BroadcastClients> broadcast = broadcastClientsDao.getAllBroadcastClients(broadcast_id);
        return broadcast;
    }
    
    public void delete(String id) {
        broadcastClientsDao.delete(id);
    }
}
