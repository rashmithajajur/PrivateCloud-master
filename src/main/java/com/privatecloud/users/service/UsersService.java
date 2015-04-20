/**
 * 
 */
package com.privatecloud.users.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.constants.AppConstants;
import com.privatecloud.users.dao.UserDao;
import com.privatecloud.users.model.UserRoles;
import com.privatecloud.users.model.Users;

/**
 * @author Rohan
 *
 */
@Service
@Transactional
public class UsersService {

	@Autowired
	private UserDao userDao;
	
	public void registerUser(Users user) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		user.setEnabled(true);
		user.setCreated(new Date());
		user.setModified(new Date());
		user.setLastlogin(new Date());
		user.assignUserRole(new UserRoles(user,AppConstants.ROLE_USER));
		
		userDao.persist(user);
	}

	public boolean isUserNameAvailable(String uname) {
		Users user = userDao.findByUserName(uname);
		return (user == null);
	}
}
