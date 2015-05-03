package com.privatecloud.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import com.privatecloud.constants.AppConstants;
import com.privatecloud.users.dao.AlarmDao;
import com.privatecloud.users.dao.UserDao;
import com.privatecloud.users.model.Alarm;
import com.privatecloud.users.model.Users;
import com.privatecloud.users.model.Vm;


@Service
@Transactional
public class AlarmService {
	
	public void alarmUser(Alarm alarm) {
		
	
		
		

}

//	@Transactional
//	public void createAlarm(Alarm alarm) {
//		InfraServices.create(alarm.getThreshold_value(), alarm.getLimit_exceed());
//		vmDao.persist(vm);
//	}
	@Autowired
	private AlarmDao alarmDao;
	
	public void alarmSet(Alarm alarm) {

		
		
		
		alarmDao.persist(alarm);
	}
	
	

//	public boolean isUserNameAvailable(String uname) {
//		Users user = userDao.findByUserName(uname);
//		return (user == null);
//	}
}
