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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.BroadcastTemplate;
import com.aquest.emailmarketing.web.dao.CampaignCategory;
import com.aquest.emailmarketing.web.dao.Campaigns;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.service.BouncedEmailService;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.BroadcastTemplateService;
import com.aquest.emailmarketing.web.service.CampaignCategoryService;
import com.aquest.emailmarketing.web.service.CampaignsService;
import com.aquest.emailmarketing.web.service.EmailConfigService;
import com.aquest.emailmarketing.web.service.EmailListService;
import com.aquest.emailmarketing.web.service.TrackingResponseService;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaignsController.
 */
@Controller
public class CampaignsController {
	
	/** The Constant logger. */
	final static Logger logger = Logger.getLogger(com.aquest.emailmarketing.web.controllers.CampaignsController.class);

    /** The campaigns service. */
    private CampaignsService campaignsService;
    
    /** The broadcast service. */
    private BroadcastService broadcastService;
    
    /** The broadcast template service. */
    private BroadcastTemplateService broadcastTemplateService;
    
    /** The email list service. */
    private EmailListService emailListService;
    
    /** The email config service. */
    private EmailConfigService emailConfigService;
    
    /** The campaign category service. */
    private CampaignCategoryService campaignCategoryService;
    
    /** The tracking response service. */
    private TrackingResponseService trackingResponseService;
    
    /** The bounced email service. */
    private BouncedEmailService bouncedEmailService;
    
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
     * Sets the email config service.
     *
     * @param emailConfigService the new email config service
     */
    @Autowired
    public void setEmailConfigService(EmailConfigService emailConfigService) {
		this.emailConfigService = emailConfigService;
	}

	/**
	 * Sets the campaign category service.
	 *
	 * @param campaignCategoryService the new campaign category service
	 */
	@Autowired
	public void setCampaignCategoryService(
			CampaignCategoryService campaignCategoryService) {
		this.campaignCategoryService = campaignCategoryService;
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
     * Sets the tracking response service.
     *
     * @param trackingResponseService the new tracking response service
     */
    @Autowired
	public void setTrackingResponseService(TrackingResponseService trackingResponseService) {
		this.trackingResponseService = trackingResponseService;
	}    
    
    /**
     * Sets the bounced email service.
     *
     * @param bouncedEmailService the new bounced email service
     */
    @Autowired
	public void setBouncedEmailService(BouncedEmailService bouncedEmailService) {
		this.bouncedEmailService = bouncedEmailService;
	}

	/**
	 * Show home.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param locale the locale
	 * @return the string
	 */
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
	
	/**
	 * Test.
	 *
	 * @return the string
	 */
	@RequestMapping(value="/test")
	public String test() {
		//TODO: Check if bounces process is finished
		bouncedEmailService.processAllBounces();
		return "test";
	}
    
    /**
     * Creates the new campaign.
     *
     * @param model the model
     * @param createCampaign the create campaign
     * @param deleteCampaign the delete campaign
     * @param editCampaign the edit campaign
     * @param openCampaign the open campaign
     * @param closeCampaign the close campaign
     * @param createBroadcast the create broadcast
     * @param campaign_id the campaign_id
     * @param principal the principal
     * @param request the request
     * @return the string
     */
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
        			bcast.setLead_number(eList.size());
        			List<EmailList> eListSent = emailListService.getSentEmailList(bcast.getBroadcast_id());
        			bcast.setSent_number(eListSent.size());
        			int openNum = trackingResponseService.getNoOfOpensByBroadcast(bcast.getBroadcast_id());
        			bcast.setOpen_number(openNum);
        			int clickNum = trackingResponseService.getNoOfClickByBroadcast(bcast.getBroadcast_id());
        			bcast.setClick_number(clickNum);
        		} else {
        			bcast.setLead_number(0);
        			bcast.setOpen_number(0);
        			bcast.setClick_number(0);
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
    
    /**
     * Show create.
     *
     * @param model the model
     * @param principal the principal
     * @return the string
     */
    @RequestMapping(value = "/createCamp")
    public String showCreate(Model model, Principal principal) {
    	List<CampaignCategory> campcat = campaignCategoryService.getCategories();
    	model.addAttribute("campcat", campcat);
    	Campaigns campaign = new Campaigns();
    	model.addAttribute("campaign", campaign);
    	return "createcampaign";
    }
    
    /**
     * Do create.
     *
     * @param campaign the campaign
     * @param result the result
     * @param principal the principal
     * @param model the model
     * @param saveCampaign the save campaign
     * @param defineBroadcast the define broadcast
     * @param fromBroadcastTemplate the from broadcast template
     * @param category_id the category_id
     * @return the string
     */
    @RequestMapping(value = "/createCampaign", method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("campaign") Campaigns campaign, BindingResult result, Principal principal, Model model,
    						@RequestParam(value = "saveCampaign", required = false) String saveCampaign,
    						@RequestParam(value = "defineBroadcast", required = false) String defineBroadcast,
    						@RequestParam(value = "fromBroadcastTemplate", required = false) String fromBroadcastTemplate,
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
    		
    		if(fromBroadcastTemplate != null) {
    			model.addAttribute("campaign", campaign);
    			Broadcast broadcast = new Broadcast();
    			model.addAttribute("broadcast", broadcast);
    			List<BroadcastTemplate> broadcastTemplate = broadcastTemplateService.getAllBroadcasts();
    			model.addAttribute("broadcastTemplate", broadcastTemplate);
    			return "pickbcasttemplate";
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
