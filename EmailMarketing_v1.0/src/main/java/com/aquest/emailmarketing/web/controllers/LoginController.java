package com.aquest.emailmarketing.web.controllers;

import java.util.List;

import javax.validation.Valid;

import com.aquest.emailmarketing.web.dao.User;
import com.aquest.emailmarketing.web.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    
        private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
        
        @RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
        
    @RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "newaccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if(usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		try {
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		
		return "accountcreated";
	}
    
    @RequestMapping(value="/userconfig", method=RequestMethod.POST)
    public String configUser(Model model,
    		@RequestParam(value = "createUser", required = false) String createUser,
    		@RequestParam(value = "editUser", required = false) String editUser,
    		@RequestParam(value = "deleteUser", required = false) String deleteUser,
    		@RequestParam(value = "username", required = false) String username) {
    	
    	if(createUser != null) {
    		model.addAttribute("user", new User());
    		return "newaccount";
    	}
    	
    	if(editUser != null) {
    		User user = usersService.getUser(username);
    		model.addAttribute("user", user);
    		return "editaccount";
    	}
    	
    	if(deleteUser != null) {
    		boolean isDeleted = usersService.delete(username);
        	if(isDeleted) {
        		String message = "confirmation.campaign.status.deleted";
            	model.addAttribute("message", message);
        		return "confirmation";
        	} else {
        		return "error";
        	}
    	}
    	return "newaccount";
    }
    
    @RequestMapping(value="/editAccount", method=RequestMethod.POST)
    public String accountEdit(Model model, User user1) {
    	User user = usersService.getUser(user1.getUsername());
    	user.setEmail(user1.getEmail());
    	user.setName(user1.getName());
    	usersService.update(user);
    	
    	List<User> users = usersService.getAllUsers();		
		model.addAttribute("users", users);
    	return "usermanagement";
    }
}
