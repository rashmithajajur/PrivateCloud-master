package com.privatecloud.users.dao;

import com.privatecloud.users.model.Users;

public interface UserDao {

	Users findByUserName(String username);

	void persist(Users transientInstance);
}