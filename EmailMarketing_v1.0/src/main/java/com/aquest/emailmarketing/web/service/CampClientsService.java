/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.aquest.emailmarketing.web.dao.CampClients;
import com.aquest.emailmarketing.web.dao.CampClientsDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bdimic
 */
@Service("campClientsService")
public class CampClientsService {
    private CampClientsDao campClientsDao;

    @Autowired
    public void setCampClientsDao(CampClientsDao campClientsDao) {
        this.campClientsDao = campClientsDao;
    }
    
    public List<CampClients> getClientsInCampaign(int i) {
        List<CampClients> campaigns = campClientsDao.getClientsInCampaign(i);
        return campaigns;
    } 
}
