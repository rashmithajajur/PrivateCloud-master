package com.privatecloud.users.dao;

//import com.privatecloud.users.model.Users;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.users.model.Vm;

@Repository
@Transactional
public interface VmDao {

	Vm findByVmName(String vmname);
    Vm findById(String Id);
    List<Vm> findAllVMs();
	void persist(Vm transientInstance);
}