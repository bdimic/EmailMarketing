package com.aquest.emailmarketing.web.controllers;

import java.security.Principal;
import java.util.List;

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

import com.aquest.emailmarketing.web.dao.EmailConfig;
import com.aquest.emailmarketing.web.dao.GaConfig;
import com.aquest.emailmarketing.web.dao.User;
import com.aquest.emailmarketing.web.service.EmailConfigService;
import com.aquest.emailmarketing.web.service.GaConfigService;
import com.aquest.emailmarketing.web.service.UsersService;

@Controller
public class AdminController {
	
	private UsersService usersService;
	private EmailConfigService emailConfigService;
	private GaConfigService gaConfigService;
	
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
	public String saveGaConfig(@Valid @ModelAttribute("gaConfig") GaConfig gaConfig, BindingResult result, Model model) {
		if(result.hasErrors()) {
    		return "gaconfiguration";
    	}		
		gaConfigService.saveOrUpdate(gaConfig);
		
		String message = "confirmation.gaconfig.status.saved";
		model.addAttribute("message", message);
		return "confirmation";
	}
}
