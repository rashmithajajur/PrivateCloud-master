package com.privatecloud.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.privatecloud.constants.AppConstants;
import com.privatecloud.users.dao.AlarmDao;
import com.privatecloud.users.dao.ParamsDao;
import com.privatecloud.users.dao.UserDao;
import com.privatecloud.users.model.Alarm;
import com.privatecloud.users.model.Params;
import com.privatecloud.users.model.Users;
import com.privatecloud.users.model.Vm;


@Service
@Transactional
public class ParamsService {
	
//	public void alarmUser(Alarm alarm) {
//		
//	
//		
//		
//
//}

//	@Transactional
//	public void createAlarm(Alarm alarm) {
//		InfraServices.create(alarm.getThreshold_value(), alarm.getLimit_exceed());
//		vmDao.persist(vm);
//	}
	@Autowired
	private ParamsDao paramsDao;
	
//	public void paramSet(Params param) {
//
//		
//		
//		
//		paramsDao.persist(param);
//	}
//	
	public List<Params> findAlllogs() {
		List<Params> vmList = paramsDao.findAlllogs();
		
		return vmList;
	} 	

//	public boolean isUserNameAvailable(String uname) {
//		Users user = userDao.findByUserName(uname);
//		return (user == null);
//	}
}
