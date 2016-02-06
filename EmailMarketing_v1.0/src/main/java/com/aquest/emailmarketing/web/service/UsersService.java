package com.aquest.emailmarketing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.User;
import com.aquest.emailmarketing.web.dao.UsersDao;

// TODO: Auto-generated Javadoc
/**
 * The Class UsersService.
 */
@Service("usersService")
public class UsersService {
	
	/** The users dao. */
	private UsersDao usersDao;
	
	/**
	 * Sets the offers dao.
	 *
	 * @param usersDao the new offers dao
	 */
	@Autowired
	public void setOffersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	
	/**
	 * Creates the.
	 *
	 * @param user the user
	 */
	public void create(User user) {
            System.out.println("I usersService se pokrenuo");
                System.out.println(user.toString());
		usersDao.create(user);
	}
	
	/**
	 * Update.
	 *
	 * @param user the user
	 */
	public void update(User user) {
		usersDao.update(user);
	}


	/**
	 * Exists.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	/**
	 * Gets the user.
	 *
	 * @param username the username
	 * @return the user
	 */
	@Secured("ROLE_ADMIN")
	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	/**
	 * Delete.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	@Secured("ROLE_ADMIN")
	public boolean delete(String username) {
		return usersDao.delete(username);
	}
}
