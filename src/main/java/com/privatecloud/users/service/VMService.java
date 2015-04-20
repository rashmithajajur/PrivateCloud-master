/**
 * 
 */
package com.privatecloud.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.users.dao.UserDao;
import com.privatecloud.users.dao.VmDao;
import com.privatecloud.users.dto.VMDto;
import com.privatecloud.users.dto.VMStatsDTO;
import com.privatecloud.users.model.Vm;

/**
 * @author Rohan
 *
 */
@Service
@Transactional
public class VMService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private VmDao vmDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public VmDao getVmDao() {
		return vmDao;
	}

	public void setVmDao(VmDao vmDao) {
		this.vmDao = vmDao;
	}

	@Transactional
	public void createVM(Vm vm) {
		InfraServices.create(vm.getVmname(), vm.getOs());
		vmDao.persist(vm);
	}

	public void home(Vm vm)
	{
		vm.getVmname();
		// vm.getStat();
		// vm.getOs();

	}

	public String Createvm( String vm1, String os1)
	{
		InfraServices.create(vm1, os1);
		return "VM Created";
	}

	public ArrayList<VMDto> Showstats()
	{
		ArrayList<VMDto> s=PrintIventory.status();

		return s;
	}

	public ArrayList<VMStatsDTO> sstats()
	{
		ArrayList<VMStatsDTO> s=StatsService.status();
		return s;
	}

	public List<Vm> findAllVMs() {
		return vmDao.findAllVMs();
	}

	public List<Vm> findAllVMsForUser(String username) {
		List<Vm> vmList = vmDao.findAllVMsForUser(username);
		List<Vm> vmListToReturn = new ArrayList<Vm>(vmList.size());
		for (Vm vm : vmList) {
			vm.setPowerOn(InfraServices.isPoweredOn(vm.getVmname()));
			vmListToReturn.add(vm);
		}
		return vmListToReturn;
	} 

	
	public boolean isVmNameAvailable(String vname) {
		Vm vmname = vmDao.findByVmName(vname);
		return (vmname == null);
	}
	
	public ArrayList<VMStatsDTO> getVMStats(String username)
	{
		List<Vm> vmsForUser = vmDao.findAllVMsForUser(username);
		ArrayList<VMStatsDTO> vmStatsList = new ArrayList<VMStatsDTO>(0);
		for (Vm vm : vmsForUser) {
			vmStatsList.add(StatsService.getVMStatsDTOs(vm.getVmname()));
		}
		return vmStatsList;
	}

	public boolean powerOn(String vmname) {
		return InfraServices.powerON(vmname);
	}

	public boolean powerOff(String vmname) {
		return InfraServices.powerOFF(vmname);
	}
	
	public boolean destroyVM(String vmname) {
		
		boolean isDestroyed = InfraServices.destroyVM(vmname);
		if(isDestroyed)
			vmDao.deleteVM(vmname);
		
		return isDestroyed;
		
	}
}


