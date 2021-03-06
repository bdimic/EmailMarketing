/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.controllers;

import com.aquest.emailmarketing.web.dao.BroadcastTemplate;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.EmbeddedImage;
import com.aquest.emailmarketing.web.dao.TrackingConfig;
import com.aquest.emailmarketing.web.dao.Urls;
import com.aquest.emailmarketing.web.service.BroadcastTemplateService;
import com.aquest.emailmarketing.web.service.EmailConfigService;
import com.aquest.emailmarketing.web.service.EmbeddedImageService;
import com.aquest.emailmarketing.web.service.TrackingConfigService;
import com.aquest.emailmarketing.web.tracking.EmailTrackingService;
import com.msiops.premailer.Premailer;
import com.msiops.premailer.PremailerInterface;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

// TODO: Auto-generated Javadoc
/**
 * The Class BroadcastTemplateController.
 */
@Controller
public class BroadcastTemplateController {
    
    /** The broadcast template service. */
    private BroadcastTemplateService broadcastTemplateService;
    
    /** The email config service. */
    private EmailConfigService emailConfigService;
    
    /** The embedded image service. */
    private EmbeddedImageService embeddedImageService;
    
    /** The tracking config service. */
    private TrackingConfigService trackingConfigService;
    
    /** The email tracking. */
    EmailTrackingService emailTracking = new EmailTrackingService();

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
     * Show broadcast template.
     *
     * @param model the model
     * @param principal the principal
     * @param locale the locale
     * @return the string
     */
    @RequestMapping("/showBroadcastTemplate")
    public String showBroadcastTemplate(Model model, Principal principal, Locale locale) {
    	List<BroadcastTemplate> broadcastTemplate = broadcastTemplateService.getAllBroadcasts();
    	model.addAttribute("broadcastTemplate", broadcastTemplate);
    	return "showbcasttemplates";
    }
    
    /**
     * New broadcast template.
     *
     * @param model the model
     * @param principal the principal
     * @return the string
     */
    @RequestMapping(value = "/newBroadcastTemplate")
    public String newBroadcastTemplate(Model model, Principal principal) {
    	BroadcastTemplate broadcastTemplate = new BroadcastTemplate();
    	List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
    	model.addAttribute("broadcastTemplate", broadcastTemplate);
    	model.addAttribute("emailconfig", emailconfig);
    	return "definebroadcasttemplate";
    }

	/**
	 * Do generate.
	 *
	 * @param model the model
	 * @param broadcastTemplate the broadcast template
	 * @param result the result
	 * @param principal the principal
	 * @param profile_id the profile_id
	 * @return the string
	 */
	@RequestMapping(value = "/generateBroadcastTemplateFlow", method = RequestMethod.POST)
    public String doGenerate(Model model,@Valid @ModelAttribute("broadcastTemplate") BroadcastTemplate broadcastTemplate,BindingResult result, Principal principal,
    						 @RequestParam(value = "profile_id") int profile_id) {
    	Timestamp curTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	broadcastTemplate.setCreation_user(principal.getName());
        broadcastTemplate.setCreation_dttm(curTimestamp);
        broadcastTemplate.getEmailConfig().setProfile_id(profile_id);
        broadcastTemplate.setStatus("In definition process");
        @SuppressWarnings("unused")
        String bcast_id = broadcastTemplateService.SaveOrUpdate(broadcastTemplate);
        	model.addAttribute("broadcastTemplate",broadcastTemplate);
        	return "definebcasttempcontent";
    }
    
    /**
     * Define content.
     *
     * @param model the model
     * @param broadcastTemplate1 the broadcast template1
     * @param result the result
     * @param principal the principal
     * @return the string
     * @throws IOException 
     */
    @RequestMapping(value= "/defineBcastTemplateContent", method = RequestMethod.POST)
    public String defineContent(Model model,@Valid @ModelAttribute("broadcastTemplate") BroadcastTemplate broadcastTemplate1,
    		@RequestParam(value = "fromUrl", required = false) String fromUrl,
			@RequestParam(value = "optimize", required = false) boolean optimize,
			@RequestParam(value = "baseurl", required = false) String baseUrl,
			@RequestParam(value = "rel2abs", required = false) boolean rel2abs,
			BindingResult result, Principal principal) throws IOException {
    	String htmlBodyPrep = "";
    	BroadcastTemplate broadcastTemplate = broadcastTemplateService.getBroadcastTemplateById(broadcastTemplate1.getId());
    	broadcastTemplate.setB_template_subject(broadcastTemplate1.getB_template_subject());
    	if(fromUrl != "") {
    		Document doc = Jsoup.connect(fromUrl).get();
    		htmlBodyPrep = doc.outerHtml();
    		broadcastTemplate.setHtmlbody(htmlBodyPrep);
    		System.out.println(htmlBodyPrep);
    	}
    	if(broadcastTemplate1.getHtmlbody() != null) {
    		htmlBodyPrep = broadcastTemplate1.getHtmlbody();
    		broadcastTemplate.setHtmlbody(htmlBodyPrep);
    	}
    	if(rel2abs == true) {
    		if(baseUrl != null) {
    			System.out.println(baseUrl);
    			Document doc = Jsoup.parse(broadcastTemplate.getHtmlbody(), baseUrl);
    			System.out.println(doc.toString());
    			
    			Elements images = doc.select("img");
    			for(Element e : images){
    				e.attr("src", e.absUrl("src"));
    				System.out.println(e.absUrl("src"));
    			}
    			broadcastTemplate.setHtmlbody(doc.outerHtml());
    			htmlBodyPrep = doc.outerHtml();
    		} else {
    			// ovde staviti error handling
    		}
    	}
    	if(optimize == true) {
//    		/* PREMAILER API OPTIONS
//    		 * line_length - Line length used by to_plain_text. Boolean, default is 65.
//    			warn_level - What level of CSS compatibility warnings to show (see Warnings).
//    				NONE = 0
//    				SAFE = 1
//    				POOR = 2
//    				RISKY = 3
//    			link_query_string - A string to append to every a href="" link. Do not include the initial ?.
//    			base_url - Used to calculate absolute URLs for local files.
//    			css - Manually specify CSS stylesheets.
//    			css_to_attributes - Copy related CSS attributes into HTML attributes (e.g. background-color to bgcolor)
//    			css_string - Pass CSS as a string
//    			remove_ids - Remove ID attributes whenever possible and convert IDs used as anchors to hashed to avoid collisions in webmail programs. Default is false.
//    			remove_classes - Remove class attributes. Default is false.
//    			remove_comments -  Remove html comments. Default is false.
//    			preserve_styles - Whether to preserve any link rel=stylesheet and style elements. Default is false.
//    			preserve_reset - Whether to preserve styles associated with the MailChimp reset code
//    			with_html_string -  Whether the html param should be treated as a raw string.
//    			verbose - Whether to print errors and warnings to $stderr. Default is false.
//    			adapter - Which HTML parser to use, either :nokogiri or :hpricot. Default is :hpricot.
//    		*/
    		Premailer premailer = new Premailer();
    		PremailerInterface premailerInterface = premailer.getPremailerInstance();
    		
    		Map<String,Object> options = new HashMap<String, Object>();
    		options.put("with_html_string", true);
    		options.put("base_url", fromUrl);
    		premailerInterface.init(broadcastTemplate.getHtmlbody(), options);
    		//premailerInterface.init(htmlBodyPrep, options);
    		broadcastTemplate.setHtmlbody(premailerInterface.inline_css());
    		System.out.println(premailerInterface.inline_css());
    		premailer.destroyInstance();
    	}
    	broadcastTemplate.setPlaintext(broadcastTemplate1.getPlaintext());
    	System.out.println(broadcastTemplate.toString());
    	String bcast_id = broadcastTemplateService.SaveOrUpdate(broadcastTemplate);
    	// Find URLs in html body and add tracking code
    	Urls urls = new Urls();
    	String html = broadcastTemplate.getHtmlbody();
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
    	
    	model.addAttribute("broadcastTemplate", broadcastTemplate);
    	return "bcasttemptracking";
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
    @RequestMapping(value= "/bcastTempGenerateUrls", method = RequestMethod.POST)
    public String addTracking(Model model, Urls urls, Principal principal,
    							@RequestParam(value = "id") int id,
    							@RequestParam(value = "trackingFlg", required = false) boolean trackingFlg,
    							@RequestParam(value = "openGAflg", required = false) boolean openGAflg,
    							@RequestParam(value = "openPixelFlg", required = false) boolean openPixelFlg,
    							@RequestParam(value = "trackingType", required = false) String trackingType){
    	TrackingConfig trackingConfig = new TrackingConfig();
    	BroadcastTemplate broadcastTemplate = broadcastTemplateService.getBroadcastTemplateById(id);
    	String workingHtml = broadcastTemplate.getHtmlbody();
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
    	
    	broadcastTemplate.setHtmlbody_tracking(workingHtml);
    	System.out.println(broadcastTemplate.getHtmlbody_tracking());
    	String confirm = broadcastTemplateService.SaveOrUpdate(broadcastTemplate);
    	System.out.println(confirm);
    	System.out.println(trackingFlg);
    	System.out.println(openGAflg);
    	System.out.println(openPixelFlg);
    	System.out.println(trackingType);
    	if(confirm == broadcastTemplate.getB_template_name()){
    		trackingConfig.setBcast_template_id(broadcastTemplate.getId());
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
    	String html = broadcastTemplate.getHtmlbody();
    	Document doc = Jsoup.parse(html);
    	Elements media = doc.select("[src]");
    	for (Element src : media) {
    		if (src.tagName().equals("img")) {
    			imgList.add(src.attr("abs:src"));
    		}
    	}
    	model.addAttribute("imgList", imgList);
    	model.addAttribute("embeddedImage", embeddedImage);
    	model.addAttribute("broadcastTemplate", broadcastTemplate);
    	return "bcasttempembeddedimage";
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
    @RequestMapping(value= "/bcastTemplateEmbedImages", method = RequestMethod.POST)
    public String embedImage(Model model, Principal principal,
			@RequestParam(value = "id") int id,
			@RequestParam(value = "url", required = false) String[] url){
    	
    	System.out.println(url.toString());
    	BroadcastTemplate broadcastTemplate = broadcastTemplateService.getBroadcastTemplateById(id);
    	System.out.println(broadcastTemplate.toString());
    	String addedTracking = broadcastTemplate.getHtmlbody_tracking();
    	
    	if(url.length > 0) {
	    	EmbeddedImage embeddedImage = new EmbeddedImage();
	    	embeddedImage.setBcast_template_id(broadcastTemplate.getId());;
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
	    	
	    	for(int k=0;k<url.length;k++) {
	    		addedTracking = addedTracking.replace(url[k], "[IMAGE:"+k+"]");
	    	}
	    	System.out.println(addedTracking);
    	}
    	broadcastTemplate.setHtmlbody_embed(addedTracking);
    	broadcastTemplate.setStatus("DEFINED");
		broadcastTemplateService.SaveOrUpdate(broadcastTemplate);
		List<BroadcastTemplate> broadcastTemplate1 = broadcastTemplateService.getAllBroadcasts();
    	model.addAttribute("broadcastTemplate", broadcastTemplate1);
    	return "showbcasttemplates";
    }

    // DONE: Add logic for pickBroadcastTemplateAction
    
    @RequestMapping(value="/pickBroadcastTemplateAction", method = RequestMethod.POST)
    public String createNewBroadcast(Model model, 
    		@RequestParam(value = "newBcastTemp", required = false) String newBcastTemp,
    		@RequestParam(value = "editBcastTemp", required = false) String editBcastTemp,
    		@RequestParam(value = "showBcastTemp", required = false) String showBcastTemp,
    		@RequestParam(value = "deleteBcastTemp", required = false) String deleteBcastTemp,
    		@RequestParam(value = "broadcast_template_id", required = false) int broadcast_template_id,
    		Principal principal, HttpServletRequest request) {
        
    	BroadcastTemplate broadcastTemplate = broadcastTemplateService.getBroadcastTemplateById(broadcast_template_id);
    	
        if(newBcastTemp != null) {
        	BroadcastTemplate newBroadcastTemplate = new BroadcastTemplate();
        	List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
        	model.addAttribute("broadcastTemplate", newBroadcastTemplate);
        	model.addAttribute("emailconfig", emailconfig);
        	return "definebroadcasttemplate";
        }
        
     // DONE: Add logic for delete Broadcast Template
        if(deleteBcastTemp != null) {
        	
        	if(broadcast_template_id != 0) {
        		boolean isDeleted = broadcastTemplateService.delete(broadcast_template_id);
        		if(isDeleted) {
        			String message = "confirmation.broadcastTemplate.status.deleted";
        			model.addAttribute("message", message);
        			return "confirmation";
    			} else {
    				return "error";
    			}
        	} else {
        		return "error";
        	}
        }
        
     // DONE: Add logic for edit Broadcast Template
        if(editBcastTemp != null) {
//        	List<CampaignCategory> campcat = campaignCategoryService.getCategories();        	
//        	Campaigns campaign = campaignsService.getCampaign(campaign_id);
//        	model.addAttribute("campaign", campaign);
//        	model.addAttribute("campcat", campcat);
        	model.addAttribute("broadcastTemplate", broadcastTemplate);
        	List<EmailConfig> emailconfig = emailConfigService.getAllProfiles();
        	model.addAttribute("emailconfig", emailconfig);
        	return "definebroadcasttemplate";
        }
        
     // DONE: Add logic for show Broadcast Template
        if(showBcastTemp != null) {
        	model.addAttribute("broadcastTemplate", broadcastTemplate);
        	return "showbcasttemplate";
        }
                
        return "home";
    }
}
