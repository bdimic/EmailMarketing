/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.Campaigns;
import com.aquest.emailmarketing.web.dao.EmailList;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessVariableService.
 */
@Service("processVariableService")
public class ProcessVariableService {
	
	/** The campaigns service. */
	private CampaignsService campaignsService;
	
	/** The broadcast service. */
	private BroadcastService broadcastService;
	
	/**
	 * Sets the campaigns service.
	 *
	 * @param campaignsService the new campaigns service
	 */
	@Autowired
    public void setCampaignsService(CampaignsService campaignsService) {
		this.campaignsService = campaignsService;
	}
    
	/**
	 * Sets the broadcast service.
	 *
	 * @param broadcastService the new broadcast service
	 */
	@Autowired
	public void setBroadcastService(BroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}



	/**
	 * Process variable.
	 *
	 * @param emailList the email list
	 * @return the hash map
	 */
	public HashMap<String, String> ProcessVariable(EmailList emailList) {
		
		Broadcast broadcast = broadcastService.getBroadcast(emailList.getBroadcast_id());
		Campaigns campaigns = campaignsService.getCampaign(broadcast.getCampaign_id());
       HashMap<String, String> variables = new HashMap<String, String>();
       Date date = Calendar.getInstance().getTime();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String newDate = sdf.format(date);
       // ovo verovatno moze pametnije
       // opste varijable idu ovde
       variables.put("DATE", newDate);
       // campaign
       variables.put("CAMPAIGN_ID", campaigns.getCampaign_id());
       variables.put("CAMPAIGN_NAME", campaigns.getCampaign_name());
       variables.put("CAMPAIGN_DESCRIPTION", campaigns.getCampaign_description());
       variables.put("CAMPAIGN_START_DATE", campaigns.getCampaign_start_date().toString());
       variables.put("CAMPAIGN_END_DATE", campaigns.getCampaign_end_date().toString());
       // broadcast
       variables.put("BROADCAST_ID", broadcast.getBroadcast_id());
       variables.put("BROADCAST_NAME", broadcast.getBroadcast_name());
       variables.put("SUBJECT", broadcast.getSubject());
       // emaillist
       variables.put("EMAIL", emailList.getEmail());
       variables.put("UNIQUE_ID", Long.toString(emailList.getId()));
       if (emailList.getName1() != null) {
    	   variables.put(emailList.getName1().toUpperCase(), emailList.getValue1());
       }
       if (emailList.getName2() != null) {
    	   variables.put(emailList.getName2().toUpperCase(), emailList.getValue2());
       }
       if (emailList.getName3() != null) {
    	   variables.put(emailList.getName3().toUpperCase(), emailList.getValue3());
       }
       if (emailList.getName4() != null) {
    	   variables.put(emailList.getName4().toUpperCase(), emailList.getValue4());
       }
       if (emailList.getName5() != null) {
    	   variables.put(emailList.getName5().toUpperCase(), emailList.getValue5());
       }
       if (emailList.getName6() != null) {
    	   variables.put(emailList.getName6().toUpperCase(), emailList.getValue6());
       }
       if (emailList.getName7() != null) {
    	   variables.put(emailList.getName7().toUpperCase(), emailList.getValue7());
       }
       if (emailList.getName8() != null) {
    	   variables.put(emailList.getName8().toUpperCase(), emailList.getValue8());
       }
       if (emailList.getName9() != null) {
    	   variables.put(emailList.getName9().toUpperCase(), emailList.getValue9());
       }
       if (emailList.getName10() != null) {
    	   variables.put(emailList.getName10().toUpperCase(), emailList.getValue10());
       }       
       return variables;
    }
}
