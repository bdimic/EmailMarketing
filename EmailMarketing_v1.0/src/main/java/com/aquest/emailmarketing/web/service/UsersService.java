package com.aquest.emailmarketing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.aquest.emailmarketing.web.dao.User;
import com.aquest.emailmarketing.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	
	private UsersDao usersDao;
	
	@Autowired
	public void setOffersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	
	public void create(User user) {
            System.out.println("I usersService se pokrenuo");
                System.out.println(user.toString());
		usersDao.create(user);
	}
	
	public void update(User user) {
		usersDao.update(user);
	}


	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDao.getAllUsers();
	}
	
	@Secured("ROLE_ADMIN")
	public User getUser(String username) {
		return usersDao.getUser(username);
	}
	
	@Secured("ROLE_ADMIN")
	public boolean delete(String username) {
		return usersDao.delete(username);
	}
}
