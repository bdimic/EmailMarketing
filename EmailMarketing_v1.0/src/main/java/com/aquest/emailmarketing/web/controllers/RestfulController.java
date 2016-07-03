/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.BcastTempOutput;
import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.BroadcastTemplate;
import com.aquest.emailmarketing.web.dao.CampaignCategory;
import com.aquest.emailmarketing.web.dao.Campaigns;
import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.dao.EmbeddedImage;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.BroadcastTemplateService;
import com.aquest.emailmarketing.web.service.CampaignCategoryService;
import com.aquest.emailmarketing.web.service.CampaignsService;
import com.aquest.emailmarketing.web.service.EmailListService;
import com.aquest.emailmarketing.web.service.EmbeddedImageService;
import com.aquest.emailmarketing.web.tracking.EmailTrackingService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BroadcastController.
 */
@Controller
public class RestfulController {
    
    /** The broadcast service. */
    private BroadcastService broadcastService;
    
    /** The broadcast template service. */
    private BroadcastTemplateService broadcastTemplateService;
    
    /** The campaigns service. */
    private CampaignsService campaignsService;
    
    /** The email list service. */
    private EmailListService emailListService;
        
    /** The embedded image service. */
    private EmbeddedImageService embeddedImageService;
   
    /** The campaign category service. */
    private CampaignCategoryService campaignCategoryService;
    
    /** The email tracking. */
    EmailTrackingService emailTracking = new EmailTrackingService();

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
     * Sets the broadcast template service.
     *
     * @param broadcastTemplateService the new broadcast template service
     */
    @Autowired
    public void setBroadcastTemplateService(BroadcastTemplateService broadcastTemplateService) {
		this.broadcastTemplateService = broadcastTemplateService;
	}
    
    /**
     * Sets the embedded image service.
     *
     * @param embeddedImageService the new embedded image service
     */
    @Autowired
    public void setEmbeddedImageService(EmbeddedImageService embeddedImageService) {
    	this.embeddedImageService = embeddedImageService;
    }
        
    /**
     * Sets the email list service.
     *
     * @param emailListService the new email list service
     */
    @Autowired
    public void setEmailListService(EmailListService emailListService) {
		this.emailListService = emailListService;
	}
    
    /**
	 * @param campaignCategoryService the campaignCategoryService to set
	 */
    @Autowired
	public void setCampaignCategoryService(CampaignCategoryService campaignCategoryService) {
		this.campaignCategoryService = campaignCategoryService;
	}

	@RequestMapping(value = "/api/broadcast", method = RequestMethod.POST)
    public ResponseEntity<Void> createBroadcastFromRest(@RequestBody Broadcast broadcast, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Broadcast " + broadcast.getBroadcast_id());
 
        if (broadcastService.isBroadcastExist(broadcast.getBroadcast_id())) {
            System.out.println("A Broadcast with id " + broadcast.getBroadcast_id() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        broadcast.setCreation_dttm(curTimestamp);
        broadcast.setStatus("In definition process");
        BroadcastTemplate bcastTemplate = broadcastTemplateService.getBroadcastTemplateById(broadcast.getBcast_template_id());
    	broadcast.setBroadcast_name(bcastTemplate.getB_template_name());
    	broadcast.setSubject(bcastTemplate.getB_template_subject());
    	broadcast.setEmailConfig(bcastTemplate.getEmailConfig());
    	broadcast.setHtmlbody(bcastTemplate.getHtmlbody());
    	broadcast.setHtmlbody_embed(bcastTemplate.getHtmlbody_embed());
    	broadcast.setHtmlbody_tracking(bcastTemplate.getHtmlbody_tracking());
    	broadcast.setPlaintext(bcastTemplate.getPlaintext());
		broadcastService.SaveOrUpdate(broadcast);
		//dodaje se red u embedded_image sa broadcast_id
		EmbeddedImage embeddedImage = embeddedImageService.getEmbeddedImagesFromTemplateId(broadcast.getBcast_template_id());
		EmbeddedImage embeddedImage1 = new EmbeddedImage();			
		embeddedImage1.setBroadcast_id(broadcast.getBroadcast_id());
		embeddedImage1.setUrl(embeddedImage.getUrl());
		embeddedImageService.SaveOrUpdate(embeddedImage1);
        
        broadcastService.SaveOrUpdate(broadcast);
 
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/api/emaillist", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmailListFromRest(@RequestBody List<EmailList> eList) {
    	
    	for(EmailList elist: eList) {
    		boolean doImport = true;
			System.out.println(elist);
			if(!broadcastService.isBroadcastExist(elist.getBroadcast_id())) {
				doImport = false;			
			}			
			if(emailListService.exists(elist.getBroadcast_id(), elist.getEmail())) {
				doImport = false;
			}
			if(elist.getEmail().isEmpty()) {
				doImport = false;
			}
			if(doImport) {
				Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
				elist.setProcess_dttm(curTimestamp);
				emailListService.SaveOrUpdate(elist);
			}
		}
    	
    	return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    //TODO: this web service is giving 400 bad request as a result
    @RequestMapping(value = "/api/campaign", method = RequestMethod.POST)
    public ResponseEntity<Void> createCampaignFromRest(@RequestBody Campaigns campaigns, UriComponentsBuilder ucBuilder) {
    	if (campaignsService.isCampaignExist(campaigns.getCampaign_id())) {
            System.out.println("A Campaign with id " + campaigns.getCampaign_id() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    	Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	campaigns.setCreation_dttm(curTimestamp);
    	campaigns.setCampaign_status("DEFINED");
    	campaignsService.SaveOrUpdate(campaigns);
    	return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/api/getbcasttemps", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BcastTempOutput>> getBcastTemplates() {
    	List<BroadcastTemplate> bcastTemps = broadcastTemplateService.getDefinedBroadcasts();
    	List<BcastTempOutput> bcastTempsOutput = new ArrayList<BcastTempOutput>();
    	for(BroadcastTemplate bcastTemp: bcastTemps) {
    		BcastTempOutput bcastTempOutput = new BcastTempOutput();
    		bcastTempOutput.setBroadcast_id(bcastTemp.getId());
    		bcastTempOutput.setBroadcast_name(bcastTemp.getB_template_name());
    		bcastTempsOutput.add(bcastTempOutput);
    	}
    	if(bcastTemps.isEmpty()) {
    		return new ResponseEntity<List<BcastTempOutput>>(HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<List<BcastTempOutput>>(bcastTempsOutput, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/getcampcat", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CampaignCategory>> getCampaignCategory() {
    	List<CampaignCategory> categories = campaignCategoryService.getCategories();
    	return new ResponseEntity<List<CampaignCategory>>(categories, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/getcampaigns", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Campaigns>> getCampaigns() {
    	List<Campaigns> campaigns = campaignsService.getCampaigns();
    	return new ResponseEntity<List<Campaigns>>(campaigns, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/getbroadcasts", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Broadcast>> getBroadcasts(@RequestParam(value="id") String campaign_id) {
    	System.out.println("Ovo se pokrenulo. Campaign_id je:" + campaign_id);
    	List<Broadcast> broadcasts = broadcastService.getDefinedBroadcastsByCampaignId(campaign_id);
    	return new ResponseEntity<List<Broadcast>>(broadcasts, HttpStatus.OK);
    }
}
