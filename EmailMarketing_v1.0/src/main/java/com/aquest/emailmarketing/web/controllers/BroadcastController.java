/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.Broadcast;
import com.aquest.emailmarketing.web.dao.EmbeddedImage;
import com.aquest.emailmarketing.web.dao.TrackingConfig;
import com.aquest.emailmarketing.web.dao.Urls;
import com.aquest.emailmarketing.web.service.BroadcastClientsService;
import com.aquest.emailmarketing.web.service.BroadcastService;
import com.aquest.emailmarketing.web.service.CampClientsService;
import com.aquest.emailmarketing.web.service.CampaignsService;
import com.aquest.emailmarketing.web.service.EmbeddedImageService;
import com.aquest.emailmarketing.web.service.SendEmailService;
import com.aquest.emailmarketing.web.service.TrackingConfigService;
import com.aquest.emailmarketing.web.tracking.EmailGoogleAnalyticsService;

import java.net.MalformedURLException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.mail.EmailException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author bdimic
 */
@Controller
public class BroadcastController {
    
    private BroadcastService broadcastService;
    private SendEmailService sendEmailService;
    private EmbeddedImageService embeddedImageService;
    private TrackingConfigService trackingConfigService;
    EmailGoogleAnalyticsService emailGoogle = new EmailGoogleAnalyticsService();

    @Autowired
    public void setBroadcastService(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @Autowired
    public void setSendEmailService(SendEmailService sendEmailService) {
    	this.sendEmailService = sendEmailService;
    }
    
    @Autowired
    public void setTrackingConfigService(TrackingConfigService trackingConfigService) {
    	this.trackingConfigService = trackingConfigService;
    }
    
    @Autowired
    public void setEmbeddedImageService(EmbeddedImageService embeddedImageService) {
    	this.embeddedImageService = embeddedImageService;
    }
    
    @RequestMapping(value = "/generateBroadcastFlow", method = RequestMethod.POST)
    public String doGenerate(Model model,@Valid @ModelAttribute("broadcast") Broadcast broadcast,BindingResult result, Principal principal,
    						 @RequestParam(value = "profile_id") int profile_id,
    						 @RequestParam(value = "campaign_id") String campaign_id) {
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
        	String bcast_id = broadcastService.SaveOrUpdate(broadcast);
        	model.addAttribute("broadcast",broadcast);
        	return "definelist";
        }
    }
    
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
    	// ovde dodati sve varijabilne podatke iz CM_EMAIL_BROADCAST_LIST
    	model.addAttribute("utmContentList", utmContentList);
    	
    	model.addAttribute("broadcast", broadcast);
    	return "tracking";
    }
    
    @RequestMapping(value= "/generateUrls", method = RequestMethod.POST)
    public String addTracking(Model model, Urls urls, Principal principal,
    							@RequestParam(value = "id") int id,
    							@RequestParam(value = "trackingFlg", required = false) boolean trackingFlg,
    							@RequestParam(value = "openGAflg", required = false) boolean openGAflg,
    							@RequestParam(value = "openPixelFlg", required = false) boolean openPixelFlg,
    							@RequestParam(value = "trackingType", required = false) String trackingType){
    	TrackingConfig trackingConfig = new TrackingConfig();
    	Broadcast broadcast = broadcastService.getBroadcastById(id);
    	broadcast.setHtmlbody(emailGoogle.addTrackingToUrl(broadcast.getHtmlbody(), urls));
    	System.out.println(broadcast.getHtmlbody());
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
    
    @RequestMapping(value= "/embedImages", method = RequestMethod.POST)
    public String embedImage(Model model, Principal principal,
			@RequestParam(value = "id") int id,
			@RequestParam(value = "url", required = false) String[] url){
    	for(int j= 0; j<url.length;j++) {
        	System.out.println(url[j]);
        }
    	Broadcast broadcast = broadcastService.getBroadcastById(id);
    	System.out.println(broadcast.toString());
    	EmbeddedImage embeddedImage = new EmbeddedImage();
    	embeddedImage.setBroadcast_id(broadcast.getBroadcast_id());
    	System.out.println(embeddedImage.getBroadcast_id());
    	// iz array u string sa ; kao separatorom
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<url.length;i++){
    		sb.append(url[i]);
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
    	String addedTracking = broadcast.getHtmlbody();
    	for(int k=0;k<url.length;k++) {
    		addedTracking = addedTracking.replace(url[k], "[IMAGE:"+k+"]");
    	}
    	System.out.println(addedTracking);
    	broadcast.setHtmlbody_embed(addedTracking);
    	broadcast.setStatus("DEFINED");
		broadcastService.SaveOrUpdate(broadcast);
		model.addAttribute("broadcast", broadcast);
    	return "sendbroadcast";
    }
    
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
}
