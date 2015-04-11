package com.privatecloud.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.users.model.Users;

@Transactional
public class UserDaoImpl implements UserDao {

	Logger log = LoggerFactory.getLogger("UserDaoImpl");
	
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Users findByUserName(String username) {

		List<Users> users = new ArrayList<Users>();

		users = getSessionFactory().getCurrentSession().createQuery("from Users where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	 public void persist(Users transientInstance) {
		 log.debug("persisting Users instance");
	        try {
	            sessionFactory.getCurrentSession().persist(transientInstance);
	            log.debug("persist successful");
	        }
	        catch (RuntimeException re) {
	            log.error("persist failed", re);
	            throw re;
	        }
	    }
}