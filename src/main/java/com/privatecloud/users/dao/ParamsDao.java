package com.privatecloud.users.dao;

import java.util.List;

import com.privatecloud.users.model.Alarm;
import com.privatecloud.users.model.Params;
import com.privatecloud.users.model.Vm;

public interface ParamsDao {

	Params findByPARAMSID(Integer paramsId);
	List<Params> findAlllogs();
//		public void persist(Alarm transientInstance);
	}


