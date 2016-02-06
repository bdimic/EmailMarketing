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


// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
public class LoginController {
    
        /** The users service. */
        private UsersService usersService;
        
        /** The roles. */
        private String[] roles = {"ROLE_ADMIN", "ROLE_USER"};
	
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
	 * Show login.
	 *
	 * @return the string
	 */
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	/**
	 * Show denied.
	 *
	 * @return the string
	 */
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	/**
	 * Show logged out.
	 *
	 * @return the string
	 */
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
        
        /**
         * Show new account.
         *
         * @param model the model
         * @return the string
         */
        @RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
        
    /**
     * Creates the account.
     *
     * @param model the model
     * @param user the user
     * @param result the result
     * @return the string
     */
    @RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(Model model, @Valid User user, BindingResult result) {
		
		if(result.hasErrors()) {
			model.addAttribute("roles", roles);
			return "newaccount";
		}
		
		//user.setAuthority("ROLE_USER");
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
    
    /**
     * Config user.
     *
     * @param model the model
     * @param createUser the create user
     * @param editUser the edit user
     * @param deleteUser the delete user
     * @param username the username
     * @return the string
     */
    @RequestMapping(value="/userconfig", method=RequestMethod.POST)
    public String configUser(Model model,
    		@RequestParam(value = "createUser", required = false) String createUser,
    		@RequestParam(value = "editUser", required = false) String editUser,
    		@RequestParam(value = "deleteUser", required = false) String deleteUser,
    		@RequestParam(value = "username", required = false) String username) {
    	
    	if(createUser != null) {
    		model.addAttribute("roles", roles);
    		model.addAttribute("user", new User());
    		return "newaccount";
    	}
    	
    	if(editUser != null) {
    		User user = usersService.getUser(username);
    		model.addAttribute("roles", roles);
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
    
    /**
     * Account edit.
     *
     * @param model the model
     * @param user1 the user1
     * @return the string
     */
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
