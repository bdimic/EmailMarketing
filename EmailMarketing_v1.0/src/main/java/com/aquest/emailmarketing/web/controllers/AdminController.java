package com.aquest.emailmarketing.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.GaConfig;
import com.aquest.emailmarketing.web.dao.User;
import com.aquest.emailmarketing.web.service.EmailConfigService;
import com.aquest.emailmarketing.web.service.GaConfigService;
import com.aquest.emailmarketing.web.service.GoogleAnalyticsService;
import com.aquest.emailmarketing.web.service.UsersService;

@Controller
public class AdminController {
	
	private UsersService usersService;
	private EmailConfigService emailConfigService;
	private GaConfigService gaConfigService;
	private GoogleAnalyticsService googleAnalyticsService;
	
	private final String filePath = "C:\\Users\\bdimic\\Documents";
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@Autowired
	public void setEmailConfigService(EmailConfigService emailConfigService) {
		this.emailConfigService = emailConfigService;
	}
	
	@Autowired
	public void setGaConfigService(GaConfigService gaConfigService) {
		this.gaConfigService = gaConfigService;
	}
	
	@Autowired
	public void setGoogleAnalyticsService(GoogleAnalyticsService googleAnalyticsService) {
		this.googleAnalyticsService = googleAnalyticsService;
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		List<User> users = usersService.getAllUsers();
		
		model.addAttribute("users", users);
		
		return "admin";
	}
	
	@RequestMapping("/usermanagement")
	public String showUsers(Model model) {
		
		List<User> users = usersService.getAllUsers();
		
		model.addAttribute("users", users);
		
		return "usermanagement";
	}
	
	@RequestMapping("/emailconfig")
	public String showAllEmailConfig(Model model) {
		
		List<EmailConfig> emailConfig = emailConfigService.getAllProfiles();
		
		model.addAttribute("emailconfig", emailConfig);
		
		return "emailconfig";
	}
	
	@RequestMapping("/emailconfiguration")
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
	
	@RequestMapping(value="/saveEmailConfig", method = RequestMethod.POST)
	public String saveConfig(@Valid @ModelAttribute("emailConfig") EmailConfig emailConfig, BindingResult result, Model model) {
		if(result.hasErrors()) {
    		return "emailconfiguration";
    	}		
		emailConfigService.saveOrUpdate(emailConfig);
		
		List<EmailConfig> emailConf = emailConfigService.getAllProfiles();		
		model.addAttribute("emailconfig", emailConf);
		
		return "emailconfig";
	}
	
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
	
	@RequestMapping(value="/saveGaConfig", method = RequestMethod.POST)
	public String saveGaConfig(Model model, HttpServletRequest request) throws Exception {
		
		String applicationName = "";
		String tableId = "";
		String apiEmail = "";
		String uploadPath = "";
		
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
                File storeFile = new File(uploadPath);
                item.write(storeFile);                
			}
		}
		
		Map<String, String> result = googleAnalyticsService.checkGaConfig(applicationName, apiEmail, uploadPath);
		
		if(result.get("result").equals("success")){
			gaConfig.setApplication_name(applicationName);
			gaConfig.setApi_email(apiEmail);
			gaConfig.setP12_key_file_name(uploadPath);
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
