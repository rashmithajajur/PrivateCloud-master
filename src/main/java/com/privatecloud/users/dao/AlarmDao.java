package com.privatecloud.users.dao;

import java.util.List;

import com.privatecloud.users.model.Alarm;
import com.privatecloud.users.model.Vm;

public interface AlarmDao {
	
	
	Alarm findByVMID(Integer vm_id);

		public void persist(Alarm transientInstance);
	}


