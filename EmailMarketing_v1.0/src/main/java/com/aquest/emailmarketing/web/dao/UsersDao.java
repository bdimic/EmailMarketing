package com.aquest.emailmarketing.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class UsersDao.
 */
@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/** The session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Session.
	 *
	 * @return the session
	 */
	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Creates the.
	 *
	 * @param user the user
	 */
	@Transactional
	public void create(User user) {
                System.out.println("Dao se pokrenuo"); 
                System.out.println(user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
                System.out.println(user.toString());
		session().save(user);
	}
	
	/**
	 * Update.
	 *
	 * @param user the user
	 */
	public void update(User user) {
		session().update(user);
	}
	
	/**
	 * Exists.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean exists(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User)crit.uniqueResult();
		return user != null;
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	}
	
	/**
	 * Gets the user.
	 *
	 * @param username the username
	 * @return the user
	 */
	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		return (User)crit.uniqueResult();
	}
	
	/**
	 * Delete.
	 *
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean delete(String username) {
        Query query = session().createQuery("delete Users b where b.username=:username");
        query.setParameter("username", username);
        return query.executeUpdate() == 1;
    }

}
