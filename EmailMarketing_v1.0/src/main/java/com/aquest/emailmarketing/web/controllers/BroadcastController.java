/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.BroadcastTemplate;
import com.aquest.emailmarketing.web.dao.CampaignCategory;
import com.aquest.emailmarketing.web.dao.Campaigns;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmailList;
import com.aquest.emailmarketing.web.dao.EmbeddedImage;
import com.aquest.emailmarketing.web.dao.TrackingConfig;
import com.aquest.emailmarketing.web.dao.Urls;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.BroadcastTemplateService;
import com.aquest.emailmarketing.web.service.CampaignsService;
import com.aquest.emailmarketing.web.service.EmailConfigService;
import com.aquest.emailmarketing.web.service.EmailListService;
import com.aquest.emailmarketing.web.service.EmbeddedImageService;
import com.aquest.emailmarketing.web.service.SendEmailService;
import com.aquest.emailmarketing.web.service.TrackingConfigService;
import com.aquest.emailmarketing.web.tracking.EmailTrackingService;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BroadcastController.
 */
@Controller
public class BroadcastController {
    
    /** The broadcast service. */
    private BroadcastService broadcastService;
    
    /** The broadcast template service. */
    private BroadcastTemplateService broadcastTemplateService;
    
    /** The campaigns service. */
    private CampaignsService campaignsService;
    
    /** The email list service. */
    private EmailListService emailListService;
    
    /** The send email service. */
    private SendEmailService sendEmailService;
    
    /** The email config service. */
    private EmailConfigService emailConfigService;
    
    /** The embedded image service. */
    private EmbeddedImageService embeddedImageService;
    
    /** The tracking config service. */
    private TrackingConfigService trackingConfigService;
    
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
	 * Sets the send email service.
	 *
	 * @param sendEmailService the new send email service
	 */
	@Autowired
    public void setSendEmailService(SendEmailService sendEmailService) {
    	this.sendEmailService = sendEmailService;
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
     * Sets the tracking config service.
     *
     * @param trackingConfigService the new tracking config service
     */
    @Autowired
    public void setTrackingConfigService(TrackingConfigService trackingConfigService) {
    	this.trackingConfigService = trackingConfigService;
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
     * Do generate from template.
     *
     * @param model the model
     * @param broadcast the broadcast
     * @param principal the principal
     * @param campaign_id the campaign_id
     * @return the string
     */
    @RequestMapping(value = "/generateBroadcastFromTemplate", method = RequestMethod.POST)
    public String doGenerateFromTemplate(Model model, @Valid @ModelAttribute("broadcast") Broadcast broadcast, Principal principal,
			 @RequestParam(value = "campaign_id") String campaign_id) {
    	Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	broadcast.setCreation_user(principal.getName());
        broadcast.setCreation_dttm(curTimestamp);
        broadcast.setBroadcast_source("EM");
        broadcast.setStatus("In definition process");
        broadcast.setCampaign_id(campaign_id);
        String broadcast_id = broadcastService.getNextBroadcastId();
        if(broadcast_id.equals("Error")){
        	return "error";
        } else {
        	broadcast.setBroadcast_id(broadcast_id);
        	BroadcastTemplate bcastTemplate = broadcastTemplateService.getBroadcastTemplateById(broadcast.getBcast_template_id());
        	broadcast.setBroadcast_name(bcastTemplate.getB_template_name());
        	broadcast.setSubject(bcastTemplate.getB_template_subject());
        	broadcast.setEmailConfig(bcastTemplate.getEmailConfig());
        	broadcast.setHtmlbody(bcastTemplate.getHtmlbody());
        	broadcast.setHtmlbody_embed(bcastTemplate.getHtmlbody_embed());
        	broadcast.setHtmlbody_tracking(bcastTemplate.getHtmlbody_tracking());
        	broadcast.setPlaintext(bcastTemplate.getPlaintext());
			String bcast_id = broadcastService.SaveOrUpdate(broadcast);
			//dodaje se red u embedded_image sa broadcast_id
			EmbeddedImage embeddedImage = embeddedImageService.getEmbeddedImagesFromTemplateId(broadcast.getBcast_template_id());
			EmbeddedImage embeddedImage1 = new EmbeddedImage();			
			embeddedImage1.setBroadcast_id(broadcast_id);
			embeddedImage1.setUrl(embeddedImage.getUrl());
			embeddedImageService.SaveOrUpdate(embeddedImage1);
        	//model.addAttribute("old_broadcast_id", old_broadcast_id);
			String message = "template";
			model.addAttribute("message", message);
        	model.addAttribute("broadcast",broadcast);
        	return "definelist";
        }
    }

	/**
	 * Do generate.
	 *
	 * @param model the model
	 * @param broadcast the broadcast
	 * @param result the result
	 * @param principal the principal
	 * @param profile_id the profile_id
	 * @param campaign_id the campaign_id
	 * @param old_broadcast_id the old_broadcast_id
	 * @return the string
	 */
	@RequestMapping(value = "/generateBroadcastFlow", method = RequestMethod.POST)
    public String doGenerate(Model model,@Valid @ModelAttribute("broadcast") Broadcast broadcast,BindingResult result, Principal principal,
    						 @RequestParam(value = "profile_id") int profile_id,
    						 @RequestParam(value = "campaign_id") String campaign_id,
    						 @RequestParam(value = "old_broadcast_id") String old_broadcast_id) {
    	Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	broadcast.setCreation_user(principal.getName());
        broadcast.setCreation_dttm(curTimestamp);
        broadcast.setBroadcast_source("EM");
        broadcast.setStatus("In definition process");
        broadcast.getEmailConfig().setProfile_id(profile_id);
        broadcast.setCampaign_id(campaign_id);
        String broadcast_id = broadcastService.getNextBroadcastId();
        if(broadcast_id.equals("Error")){
        	return "error";
        } else {
        	broadcast.setBroadcast_id(broadcast_id);
        	@SuppressWarnings("unused")
			String bcast_id = broadcastService.SaveOrUpdate(broadcast);
        	model.addAttribute("old_broadcast_id", old_broadcast_id);
        	model.addAttribute("broadcast",broadcast);
        	return "definelist";
        }
    }
    
    /**
     * Define content.
     *
     * @param model the model
     * @param broadcast1 the broadcast1
     * @param result the result
     * @param principal the principal
     * @return the string
     */
    @RequestMapping(value= "/defineContent", method = RequestMethod.POST)
    public String defineContent(Model model,@Valid @ModelAttribute("broadcast") Broadcast broadcast1,BindingResult result, Principal principal) {
    	Broadcast broadcast = broadcastService.getBroadcastById(broadcast1.getId());
    	broadcast.setSubject(broadcast1.getSubject());
    	broadcast.setHtmlbody(broadcast1.getHtmlbody());
    	broadcast.setPlaintext(broadcast1.getPlaintext());
    	broadcastService.SaveOrUpdate(broadcast);
    	// Find URLs in html body and add tracking code
    	Urls urls = new Urls();
    	String html = broadcast.getHtmlbody();
    	List<String> urlList = new ArrayList<String>();
    	Document doc = Jsoup.parse(html);
    	Elements links = doc.select("a[href]");
    	for (Element link : links){
    		if(link.attr("abs:href").length()>5){
    			urlList.add(link.attr("abs:href"));
    		}
    	}
    	model.addAttribute("urlList", urlList);
    	model.addAttribute("urls", urls);
    	
    	// Google Analytics - utmCampaign List
    	List<String> utmCampaignList = new ArrayList<String>();    	
    	utmCampaignList.add("[BROADAST_NAME]");
    	model.addAttribute("utmCampaignList", utmCampaignList);
    	
    	// Google Analytics - utmSource List
    	List<String> utmSourceList = new ArrayList<String>();    	
    	utmSourceList.add("[CAMPAIGN_NAME]");
    	model.addAttribute("utmSourceList", utmSourceList);
    	
    	// Google Analytics - utmContent List
    	List<String> utmContentList = new ArrayList<String>();    	
    	utmContentList.add("[EMAIL]");
    	//TODO: add all variables from CM_EMAIL_BROADCAST_LIST
    	model.addAttribute("utmContentList", utmContentList);
    	
    	model.addAttribute("broadcast", broadcast);
    	return "tracking";
    }
    
    /**
     * Adds the tracking.
     *
     * @param model the model
     * @param urls the urls
     * @param principal the principal
     * @param id the id
     * @param trackingFlg the tracking flg
     * @param openGAflg the open g aflg
     * @param openPixelFlg the open pixel flg
     * @param trackingType the tracking type
     * @return the string
     */
    @RequestMapping(value= "/generateUrls", method = RequestMethod.POST)
    public String addTracking(Model model, Urls urls, Principal principal,
    							@RequestParam(value = "id") int id,
    							@RequestParam(value = "trackingFlg", required = false) boolean trackingFlg,
    							@RequestParam(value = "openGAflg", required = false) boolean openGAflg,
    							@RequestParam(value = "openPixelFlg", required = false) boolean openPixelFlg,
    							@RequestParam(value = "trackingType", required = false) String trackingType){
    	TrackingConfig trackingConfig = new TrackingConfig();
    	Broadcast broadcast = broadcastService.getBroadcastById(id);
    	String workingHtml = broadcast.getHtmlbody();
    	if(trackingFlg == true) {
    		if(openGAflg == true) {
    			workingHtml = emailTracking.addGaOpenEmailTracking(workingHtml, urls);
    			System.out.println("GA Open: "+workingHtml);
    		}
    		if(openPixelFlg == true) {
    			workingHtml = emailTracking.addPixelOpenEmailTracking(workingHtml);
    			System.out.println("Pixel Open: "+workingHtml);
    		}
    		if(trackingType.equals("ga")) {
    			workingHtml = emailTracking.addGaTrackingToUrl(workingHtml, urls);
    			System.out.println("GA Click added: "+workingHtml);
    		} else if (trackingType.equals("intTrack")) {
    			workingHtml = emailTracking.addIntTrackingToUrl(workingHtml, urls);
    			System.out.println("Internal Tracking: "+workingHtml);
    		} else {
    			workingHtml = emailTracking.addBothTrackingToUrl(workingHtml, urls);
    		}
    		
    	}
    	
    	broadcast.setHtmlbody_tracking(workingHtml);
    	System.out.println(broadcast.getHtmlbody_tracking());
    	String confirm = broadcastService.SaveOrUpdate(broadcast);
    	System.out.println(confirm);
    	System.out.println(trackingFlg);
    	System.out.println(openGAflg);
    	System.out.println(openPixelFlg);
    	System.out.println(trackingType);
    	if(confirm == broadcast.getBroadcast_id()){
    		trackingConfig.setBroadcast_id(broadcast.getBroadcast_id());
    		// taking care of tracking flg
    		int tracking_flg = 0;
    		if(trackingFlg == true) {
    			tracking_flg = 1;
    		}
    		trackingConfig.setTracking_flg(tracking_flg);
    		// taking care of openGAflg
    		int open_ga_flg = 0;
    		if (openGAflg == true) {
    			open_ga_flg = 1;
    		}
    		trackingConfig.setOpen_ga_flg(open_ga_flg);
    		// taking care of openPixelFlg
    		int open_pixel_flg = 0;
    		if (openPixelFlg == true) {
    			open_pixel_flg = 1;
    		}
    		trackingConfig.setOpen_pixel_flg(open_pixel_flg);
    		// set tracking type
    		trackingConfig.setTracking_type(trackingType);
    		// seting utm's
    		trackingConfig.setUtm_campaign(urls.getUtmCampaign());
    		trackingConfig.setUtm_content(urls.getUtmContent());
    		trackingConfig.setUtm_medium(urls.getUtmMedium());
    		trackingConfig.setUtm_source(urls.getUtmSource());
    		trackingConfigService.SaveOrUpdate(trackingConfig);
    	}
    	// find images in html to be able to embed images in email as in-line attachments
    	EmbeddedImage embeddedImage = new EmbeddedImage();
    	List<String> imgList = new ArrayList<String>();
    	String html = broadcast.getHtmlbody();
    	Document doc = Jsoup.parse(html);
    	Elements media = doc.select("[src]");
    	for (Element src : media) {
    		if (src.tagName().equals("img")) {
    			imgList.add(src.attr("abs:src"));
    		}
    	}
    	model.addAttribute("imgList", imgList);
    	model.addAttribute("embeddedImage", embeddedImage);
    	model.addAttribute("broadcast", broadcast);
    	return "embeddedimage";
    }
    
    
    
    /**
     * Embed image.
     *
     * @param model the model
     * @param principal the principal
     * @param id the id
     * @param url the url
     * @return the string
     */
    @RequestMapping(value= "/embedImages", method = RequestMethod.POST)
    public String embedImage(Model model, Principal principal,
			@RequestParam(value = "id") int id,
			@RequestParam(value = "url", required = false, defaultValue = "nesto") List<String> url){
    	
    	//DONE: NullPointerException when no pictures is selected
    	
    	System.out.println(url.toString());
    	Broadcast broadcast = broadcastService.getBroadcastById(id);
    	System.out.println(broadcast.toString());
    	String addedTracking = broadcast.getHtmlbody_tracking();
    	
    	if(url.size() > 0 && !url.get(0).equals("nesto")) {
	    	EmbeddedImage embeddedImage = new EmbeddedImage();
	    	embeddedImage.setBroadcast_id(broadcast.getBroadcast_id());
	    	System.out.println(embeddedImage.getBroadcast_id());
	    	// iz array u string sa ; kao separatorom
	    	StringBuilder sb = new StringBuilder();
	    	for(int i=0;i<url.size();i++){
	    		sb.append(url.get(i));
	    		sb.append(";");
	    	}
	    	System.out.println(sb.toString());
	    	
	    	String urls = sb.toString();
	    	System.out.println(urls);
	    	if (urls.charAt(urls.length()-1)==';')
	        {
	    		urls = urls.substring(0,urls.length()-1);
	        }
	    	System.out.println(urls);
	    	
	    	embeddedImage.setUrl(urls);
	    	embeddedImageService.SaveOrUpdate(embeddedImage);
	    	
	    	for(int k=0;k<url.size();k++) {
	    		addedTracking = addedTracking.replace(url.get(k), "[IMAGE:"+k+"]");
	    	}
	    	System.out.println(addedTracking);
    	}
    	broadcast.setHtmlbody_embed(addedTracking);
    	broadcast.setStatus("DEFINED");
		broadcastService.SaveOrUpdate(broadcast);
		model.addAttribute("broadcast", broadcast);
    	return "sendbroadcast";
    }
    
    /**
     * Send it.
     *
     * @param model the model
     * @param principal the principal
     * @param id the id
     * @param send the send
     * @param request the request
     * @return the string
     */
    @RequestMapping(value= "/sendBroadcast", method = RequestMethod.POST)
    public String sendIt(Model model, Principal principal,
    		@RequestParam(value = "id") int id,
			@RequestParam(value = "send", required = false) String send,
			HttpServletRequest request) {
    	Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	System.out.println(String.valueOf(id));
    	Broadcast broadcast = broadcastService.getBroadcastById(id);
    	System.out.println(broadcast);
    	sendEmailService.sendEmails(broadcast);
    	broadcast.setStatus("SENT");
    	broadcast.setExecution_user(principal.getName());
    	broadcast.setExecution_dttm(curTimestamp);
    	broadcastService.SaveOrUpdate(broadcast);
    	return "sentbroadcast";
    }
    
    
    /**
     * Creates the new broadcast.
     *
     * @param model the model
     * @param newBroadcast the new broadcast
     * @param copyBroadcast the copy broadcast
     * @param editBroadcast the edit broadcast
     * @param showBroadcast the show broadcast
     * @param deleteBroadcast the delete broadcast
     * @param campaign_id the campaign_id
     * @param broadcast_id the broadcast_id
     * @param principal the principal
     * @param request the request
     * @return the string
     */
    @RequestMapping(value="/pickBroadcastAction", method = RequestMethod.POST)
    public String createNewBroadcast(Model model, 
    		@RequestParam(value = "newBroadcast", required = false) String newBroadcast,
    		@RequestParam(value = "copyBroadcast", required = false) String copyBroadcast,
    		@RequestParam(value = "editBroadcast", required = false) String editBroadcast,
    		@RequestParam(value = "showBroadcast", required = false) String showBroadcast,
    		@RequestParam(value = "deleteBroadcast", required = false) String deleteBroadcast,
    		@RequestParam(value = "campaign_id", required = false) String campaign_id,
    		@RequestParam(value = "broadcast_id", required = false) String broadcast_id,
    		Principal principal, HttpServletRequest request) {
        
    	Broadcast broadcast = broadcastService.getBroadcast(broadcast_id);
    	
        if(newBroadcast != null) {
        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
        	Broadcast broadcast1 = new Broadcast();
        	List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
        	model.addAttribute("campaign", campaign);
        	model.addAttribute("broadcast", broadcast1);
        	model.addAttribute("emailconfig", emailconfig);
        	return "definebroadcast";
        }
        
        if(copyBroadcast != null) {
        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
        	List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
        	model.addAttribute("campaign", campaign);
        	model.addAttribute("broadcast", broadcast);
        	model.addAttribute("emailconfig", emailconfig);
        	return "definebroadcast";
        }
        
        if(deleteBroadcast != null) {
        	
        	if(broadcast != null) {
        		if(broadcast.getStatus().equals("SENT")) {
        			String message = "confirmation.broadcast.status.nodelete";
        			model.addAttribute("message", message);
        			return "confirmation";
        		} else {
        			boolean isDeleted = broadcastService.delete(broadcast.getId());
            		if(isDeleted) {
            			boolean trackingDeleted = trackingConfigService.delete(broadcast_id);
            			if(trackingDeleted) {
	            			String message = "confirmation.broadcast.status.deleted";
	            			model.addAttribute("message", message);
	            			return "confirmation";
            			} else {
            				return "error";
            			}
            		} else {
            			return "error";
            		}
        		}
        		
        	} else {
        		return "error";
        	}
        }
        
        if(editBroadcast != null) {
        	// DONE: Create logic for edit Broadcast option
        	// DONE: Add that if broadcast is in "SENT" status, edit is not possible and appropriate message appear
        	if(!broadcast.getStatus().equals("SENT")){
	        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
	        	model.addAttribute("campaign", campaign);
	        	model.addAttribute("broadcast", broadcast);
	        	List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
	        	model.addAttribute("emailconfig", emailconfig);
	        	return "definebroadcast";
        	} else {
        		String message = "confirmation.broadcast.no.edit";
    			model.addAttribute("message", message);
    			return "confirmation";
        	}
        }
        
        if(showBroadcast != null) {
        	// DONE: Create logic for show Broadcast option
        	model.addAttribute("broadcast", broadcast); 
        	return "showbroadcast";
        }
                
        return "home";
    }
}
