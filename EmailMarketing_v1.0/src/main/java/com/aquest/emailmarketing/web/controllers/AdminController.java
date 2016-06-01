package com.aquest.emailmarketing.web.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aquest.emailmarketing.web.dao.CampaignCategory;
import com.aquest.emailmarketing.web.dao.Config;
import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.GaConfig;
import com.aquest.emailmarketing.web.dao.User;
import com.aquest.emailmarketing.web.service.CampaignCategoryService;
import com.aquest.emailmarketing.web.service.ConfigService;
import com.aquest.emailmarketing.web.service.EmailConfigService;
import com.aquest.emailmarketing.web.service.GaConfigService;
import com.aquest.emailmarketing.web.service.GoogleAnalyticsService;
import com.aquest.emailmarketing.web.service.UsersService;
import com.google.api.client.util.SecurityUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminController.
 */
@Controller
public class AdminController {
	
	/** The users service. */
	private UsersService usersService;
	
	/** The email config service. */
	private EmailConfigService emailConfigService;
	
	/** the campaign category service */
	private CampaignCategoryService campaignCategoryService;
	
	/** The ga config service. */
	private GaConfigService gaConfigService;
	
	/** The google analytics service. */
	private GoogleAnalyticsService googleAnalyticsService;
	
	/** The config service. */
	private ConfigService configService;
	
	/** The file path. */
	private final String filePath = "C:\\Users\\bdimic\\Documents";
	
	/**
	 * Sets the users service.
	 *
	 * @param usersService the new users service
	 */
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
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
	 * @param campaignCategoryService the campaignCategoryService to set
	 */
	@Autowired
	public void setCampaignCategoryService(CampaignCategoryService campaignCategoryService) {
		this.campaignCategoryService = campaignCategoryService;
	}

	/**
	 * Sets the ga config service.
	 *
	 * @param gaConfigService the new ga config service
	 */
	@Autowired
	public void setGaConfigService(GaConfigService gaConfigService) {
		this.gaConfigService = gaConfigService;
	}
	
	/**
	 * Sets the google analytics service.
	 *
	 * @param googleAnalyticsService the new google analytics service
	 */
	@Autowired
	public void setGoogleAnalyticsService(GoogleAnalyticsService googleAnalyticsService) {
		this.googleAnalyticsService = googleAnalyticsService;
	}
	
	
	/**
	 * Sets the config service.
	 * 
	 * @param configService the configService to set
	 */
	@Autowired
	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	/**
	 * Show admin.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		List<User> users = usersService.getAllUsers();
		
		model.addAttribute("users", users);
		
		return "admin";
	}
	
	/**
	 * Show users.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/usermanagement")
	public String showUsers(Model model) {
		
		List<User> users = usersService.getAllUsers();
		
		model.addAttribute("users", users);
		
		return "usermanagement";
	}
	
	/**
	 * Show all email config.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/emailconfig")
	public String showAllEmailConfig(Model model) {
		
		List<EmailConfig> emailConfig = emailConfigService.getAllProfiles();
		
		model.addAttribute("emailconfig", emailConfig);
		
		return "emailconfig";
	}
	
	/**
	 * Show all campaign category.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping("/showcampcat")
	public String showAllCampCat(Model model) {
		
		List<CampaignCategory> campcat = campaignCategoryService.getCategories();
		
		model.addAttribute("campcat", campcat);
		
		return "showcampcat";
	}
	
	/**
	 * Show email config.
	 *
	 * @param model the model
	 * @param createProfile the create profile
	 * @param editProfile the edit profile
	 * @param deleteProfile the delete profile
	 * @param id the id
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value="/emailconfiguration", method = RequestMethod.POST)
	public String showEmailConfig(Model model,
			@RequestParam(value = "createProfile", required = false) String createProfile,
    		@RequestParam(value = "editProfile", required = false) String editProfile,
    		@RequestParam(value = "deleteProfile", required = false) String deleteProfile,
    		@RequestParam(value = "id", required = false) int id,
    		Principal principal, HttpServletRequest request) {
		if(createProfile != null) {
			EmailConfig emailConfig = new EmailConfig();
			model.addAttribute("emailConfig", emailConfig);
			return "emailconfiguration";
		}
		
		if(editProfile != null) {
			EmailConfig emailConfig = emailConfigService.getProfileById(id);
			model.addAttribute("emailConfig", emailConfig);
			return "emailconfiguration";
		}
		
		if(deleteProfile != null) {
			boolean isDeleted = emailConfigService.delete(id);
        	if(isDeleted) {
        		String message = "confirmation.emailconfig.status.deleted";
            	model.addAttribute("message", message);
        		return "confirmation";
        	} else {
        		return "error";
        	}
		}
		return "emailconfig";
	}
	
	/**
	 * Show email config.
	 *
	 * @param model the model
	 * @param createProfile the create profile
	 * @param editProfile the edit profile
	 * @param deleteProfile the delete profile
	 * @param id the id
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping(value="/definecampcat", method = RequestMethod.POST)
	public String defineCampCat(Model model,
			@RequestParam(value = "addCategory", required = false) String addCategory,
    		@RequestParam(value = "editCategory", required = false) String editCategory,
    		@RequestParam(value = "deleteCategory", required = false) String deleteCategory,
    		@RequestParam(value = "id", required = false) int id,
    		Principal principal, HttpServletRequest request) {
		if(addCategory != null) {
			CampaignCategory campcat = new CampaignCategory();
			model.addAttribute("campcat", campcat);
			return "definecampcat";
		}
		
		if(editCategory != null) {
			CampaignCategory campcat = campaignCategoryService.getCategoryById(id);
			model.addAttribute("campcat", campcat);
			return "definecampcat";
		}
		
		if(deleteCategory != null) {
			boolean isDeleted = campaignCategoryService.delete(id);
        	if(isDeleted) {
        		String message = "confirmation.emailconfig.status.deleted";
            	model.addAttribute("message", message);
        		return "confirmation";
        	} else {
        		return "error";
        	}
		}
		return "showcampcat";
	}
	
	/**
	 * Save category.
	 *
	 * @param campaignCategory the campaign category
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value="/saveCampaignCategory", method = RequestMethod.POST)
	public String saveCampaignCategory(@Valid @ModelAttribute("campcat") CampaignCategory campaignCategory, BindingResult result, Model model) {
		if(result.hasErrors()) {
    		return "definecampcat";
    	}
		System.out.println(campaignCategory.getCategory_id());
		campaignCategoryService.SaveOrUpdate(campaignCategory);
		
		List<CampaignCategory> campcat = campaignCategoryService.getCategories();		
		model.addAttribute("campcat", campcat);
		
		return "showcampcat";
	}
	
	@RequestMapping("/config")
	public String showConfig(Model model) {
		List<Config> configs = configService.getAllConfigs();
		for(Config config : configs) {
			if(config.getCategory().equals("trackingurl")){
				model.addAttribute("trackingurl", config.getValue());
			}
		}
		return "config";
	}
	
	@RequestMapping(value="/saveConfig", method = RequestMethod.POST)
	public String saveConfig(Model model, Principal principal,
								@RequestParam(value = "trackingurl") String trackingurl) {
		
		Config config = new Config();
		config.setCategory("trackingurl");
		config.setValue(trackingurl);
		configService.saveOrUpdate(config);
		
		return "config";
	}
	
	/**
	 * Save config.
	 *
	 * @param emailConfig the email config
	 * @param result the result
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value="/saveEmailConfig", method = RequestMethod.POST)
	public String saveEmailConfig(@Valid @ModelAttribute("emailConfig") EmailConfig emailConfig, BindingResult result, Model model) {
		if(result.hasErrors()) {
    		return "emailconfiguration";
    	}
		emailConfigService.saveOrUpdate(emailConfig);
		
		List<EmailConfig> emailConf = emailConfigService.getAllProfiles();		
		model.addAttribute("emailconfig", emailConf);
		
		return "emailconfig";
	}
	
	/**
	 * Show ga config.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 */
	@RequestMapping("/gaconfiguration")
	public String showGaConfig(Model model,
    		Principal principal, HttpServletRequest request) {
		
		GaConfig gaConfig = gaConfigService.getGaConfig();
		if(gaConfig == null) {
			gaConfig = new GaConfig();
		}
		model.addAttribute("gaConfig", gaConfig);		
		return "gaconfiguration";
	}
	
	/**
	 * Save ga config.
	 *
	 * @param model the model
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value="/saveGaConfig", method = RequestMethod.POST)
	public String saveGaConfig(Model model, HttpServletRequest request) throws Exception {
		
		String applicationName = "";
		String apiEmail = "";
		String uploadPath = "";
		File storeFile = null;
		
		GaConfig gaConfig = gaConfigService.getGaConfig();
		if(gaConfig == null) {
			gaConfig = new GaConfig();
		}
		
		//GaConfig gaConfig = new GaConfig();
				
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		//List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		for (FileItem item : items) {
			if (item.isFormField()){
				String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                if (fieldName.equals("application_name")) {
                	applicationName = fieldValue;                	
                } else if(fieldName.equals("api_email")) {
                	apiEmail = fieldValue;                	
                }
			} else {
				//String fieldName = item.getFieldName();
                String fileName = FilenameUtils.getName(item.getName());
                uploadPath = filePath+File.separator+fileName;
                storeFile = new File(uploadPath);
                item.write(storeFile);                
			}
		}
		
		Map<String, String> result = googleAnalyticsService.checkGaConfig(applicationName, apiEmail, uploadPath);
		
		if(result.get("result").equals("success")){
			gaConfig.setApplication_name(applicationName);
			gaConfig.setApi_email(apiEmail);
			gaConfig.setP12_key_file_name(uploadPath);
			KeyStore keystore = KeyStore.getInstance("PKCS12");
			keystore.load(this.getClass().getClassLoader().getResourceAsStream(uploadPath), "notasecret".toCharArray());
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			keystore.store(byteArrayOutputStream, "notasecret".toCharArray());
			byte[] keyStream = byteArrayOutputStream.toByteArray();
			//InputStream is = new FileInputStream(storeFile);
			//byte[] p12 = IOUtils.toByteArray(is);
			gaConfig.setP12_file(keyStream);
			gaConfig.setTable_id(result.get("profileId"));
			System.out.println(gaConfig.toString());
			gaConfigService.saveOrUpdate(gaConfig);		
			String message = "confirmation.gaconfig.status.saved";
			model.addAttribute("message", message);
			return "confirmation";
		} else {
			String message = result.get("result");
			model.addAttribute("message", message);
			return "confirmation";
		}
	}
}
