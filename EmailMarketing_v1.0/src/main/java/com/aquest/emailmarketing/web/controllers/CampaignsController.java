/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.CampaignCategory;
import com.aquest.emailmarketing.web.dao.Campaigns;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.CampaignCategoryService;
import com.aquest.emailmarketing.web.service.CampaignsService;
import com.aquest.emailmarketing.web.service.EmailConfigService;
import com.aquest.emailmarketing.web.service.EmailListService;

/**
 *
 * @author bdimic
 */
@Controller
public class CampaignsController {

    private CampaignsService campaignsService;
    private BroadcastService broadcastService;
    private EmailListService emailListService;
    private EmailConfigService emailConfigService;
    private CampaignCategoryService campaignCategoryService;
    
    @Autowired
    public void setCampaignsService(CampaignsService campaignsService) {
        this.campaignsService = campaignsService;
    }
    
    @Autowired
    public void setBroadcastService(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }
    
    @Autowired
    public void setEmailConfigService(EmailConfigService emailConfigService) {
		this.emailConfigService = emailConfigService;
	}    
    
    @Autowired
	public void setCampaignCategoryService(
			CampaignCategoryService campaignCategoryService) {
		this.campaignCategoryService = campaignCategoryService;
	}
        
    @Autowired
	public void setEmailListService(EmailListService emailListService) {
		this.emailListService = emailListService;
	}

	@RequestMapping("/")
    public String showHome(Model model, Principal principal, Locale locale) {
        String username = principal.getName();        
        List<Campaigns> campaigns = campaignsService.getCampaigns();
        for(Campaigns camp: campaigns) {
        	List<Broadcast> broadcasts = broadcastService.getBroadcastsByCampaignId(camp.getCampaign_id());
        	camp.setBroadcast_number(broadcasts.size());
        }
        model.addAttribute("username", username);
        model.addAttribute("campaigns", campaigns);
        return "home";
    }
	
	@RequestMapping(value="/test")
	public String test() {
		return "test";
	}
    
    @RequestMapping(value="/pickCampaignAction", method = RequestMethod.POST)
    public String createNewCampaign(Model model, 
    		@RequestParam(value = "createCampaign", required = false) String createCampaign,
    		@RequestParam(value = "deleteCampaign", required = false) String deleteCampaign,
    		@RequestParam(value = "editCampaign", required = false) String editCampaign,
    		@RequestParam(value = "openCampaign", required = false) String openCampaign,
    		@RequestParam(value = "closeCampaign", required = false) String closeCampaign,
    		@RequestParam(value = "createBroadcast", required = false) String createBroadcast,
    		@RequestParam(value = "campaign_id", required = false) String campaign_id,
    		Principal principal, HttpServletRequest request) {
        
    	List<Broadcast> broadcast = broadcastService.getBroadcastsByCampaignId(campaign_id);
    	
        if(createCampaign != null) {
        	List<CampaignCategory> campcat = campaignCategoryService.getCategories();
        	model.addAttribute("campcat", campcat);
        	Campaigns campaign = new Campaigns();
        	model.addAttribute("campaign", campaign);
        	return "createcampaign";
        }
        
        if(deleteCampaign != null) {
        	// ubaciti logiku koja proverava dali za kampanju za brisanje postoji definisan broadcast. ukoliko postoji
        	// kampanju moze obrisati samo administrator. ukoliko postoji broadcast koji je u statusu sent nije moguce brisanje
        	// ni administratoru
        	if(broadcast != null) {
        		int sentBroadcast = 0;
        		for(int i=0; i<broadcast.size();i++) {
        			if(broadcast.get(i).getStatus().equals("SENT")) {
        				sentBroadcast++;
        			}
        		}
        		if(sentBroadcast > 0) {
        			String message = "confirmation.campaign.status.nodelete";
        			model.addAttribute("message", message);
        			return "confirmation";
        		} else {
        			Campaigns campaign = campaignsService.getCampaign(campaign_id);
                	model.addAttribute("broadcast", broadcast);
                	model.addAttribute("campaign", campaign);
                	CampaignCategory campcat = campaignCategoryService.getCategoryById(campaign.getCampaignCategory().getCategory_id());
                	model.addAttribute("campcat", campcat);
            		return "opencampaign";
        		}
        		
        	} else {
        	boolean isDeleted = campaignsService.delete(campaign_id);
        		if(isDeleted) {
        			String message = "confirmation.campaign.status.deleted";
        			model.addAttribute("message", message);
        			return "confirmation";
        		} else {
        			return "error";
        		}
        	}
        }
        
        if(editCampaign != null) {
        	List<CampaignCategory> campcat = campaignCategoryService.getCategories();        	
        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
        	model.addAttribute("campaign", campaign);
        	model.addAttribute("campcat", campcat);
        	return "editcampaign";
        }
        
        if(openCampaign != null) {
        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
        	for(Broadcast bcast: broadcast) {
        		if(bcast.getStatus().equals("SENT")) {
        			List<EmailList> eList = emailListService.getAllEmailList(bcast.getBroadcast_id());
        			
        		}
        	}
        	model.addAttribute("broadcast", broadcast);
        	model.addAttribute("campaign", campaign);
        	System.out.println(campaign.getCampaignCategory().getCategory_id());
        	CampaignCategory campcat = campaignCategoryService.getCategoryById(campaign.getCampaignCategory().getCategory_id());
        	model.addAttribute("campcat", campcat);
        	
        	return "opencampaign";
        }
        
        if(closeCampaign != null) {
        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
        	campaign.setCampaign_status("CLOSED");
        	campaignsService.SaveOrUpdate(campaign);
        	String message = "confirmation.campaign.status.changed";
        	System.out.println(message);
        	model.addAttribute("message", message);
        	return "confirmation";
        }
        
        if(createBroadcast != null) {
        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
        	Broadcast broadcast1 = new Broadcast();
        	List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
        	model.addAttribute("campaign", campaign);
        	model.addAttribute("broadcast", broadcast1);
        	model.addAttribute("emailconfig", emailconfig);
        	return "definebroadcast";
        }
        
        return "home";
    }    
    
    @RequestMapping(value = "/createCampaign", method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("campaign") Campaigns campaign, BindingResult result, Principal principal, Model model,
    						@RequestParam(value = "saveCampaign", required = false) String saveCampaign,
    						@RequestParam(value = "defineBroadcast", required = false) String defineBroadcast,
    						@RequestParam(value = "category_id") int category_id) {
    	
    	if(result.hasErrors()) {
    		List<CampaignCategory> campcat = campaignCategoryService.getCategories();
        	model.addAttribute("campcat", campcat);
    		return "createcampaign";
    	}
    	
    	if(campaign.getCampaign_id()==null){
    		// this is for new campaign creation
    		Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    		campaign.setCreation_user(principal.getName());
    		campaign.setCreation_dttm(curTimestamp);
    		campaign.setCampaign_status("DEFINED");
    		campaign.getCampaignCategory().setCategory_id(category_id);
    		campaign.setCampaign_source("EM");
    		String campaign_id = campaignsService.getNextCampaignId();
    		if(campaign_id.equals("Error")) {
    			return "error";
    		} else {
    			campaign.setCampaign_id(campaign_id);
    			campaignsService.SaveOrUpdate(campaign);
    		}
    		
    		
    		if(saveCampaign != null) {
    			return "campaigncreated";
    		}
    		if(defineBroadcast != null) {
    			List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
    			model.addAttribute("campaign", campaign);
    			System.out.println(emailconfig.toString());
    			model.addAttribute("emailconfig", emailconfig);
    			System.out.println(campaign.toString());
    			Broadcast broadcast = new Broadcast();
    			model.addAttribute("broadcast", broadcast);    			
    			return "definebroadcast";
    		}
    			return "error";
    	} else {
    		// this is for changing campaign
    		Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    		campaign.setLast_change_user(principal.getName());
    		campaign.setLast_change_dttm(curTimestamp);
    		campaign.getCampaignCategory().setCategory_id(category_id);
    		campaignsService.SaveOrUpdate(campaign);
    		return "campaigncreated"; //ovde treba promeniti u campaignchanged stranu
    	}    	
    }
}
