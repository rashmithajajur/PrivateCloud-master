/**
 * 
 */
package com.privatecloud.users.service;

import java.util.ArrayList;
import java.util.List;

import com.privatecloud.users.service.HelloVM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.users.dao.UserDao;
import com.privatecloud.users.dao.VmDao;
import com.privatecloud.users.dto.VMDto;
import com.privatecloud.users.dto.vmstat;
import com.privatecloud.users.model.Vm;
import com.privatecloud.users.service.PrintIventory;
import com.privatecloud.users.service.stats;
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
	
//	public UserDetails loadByUsername(final String username) throws UsernameNotFoundException {
//
//		// Programmatic transaction management
//		/*
//		return transactionTemplate.execute(new TransactionCallback<UserDetails>() {
//
//			public UserDetails doInTransaction(TransactionStatus status) {
//				com.privatecloud.users.model.Users user = userDao.findByUserName(username);
//				List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
//
//				return buildUserForAuthentication(user, authorities);
//			}
//
//		});*/
//		com.privatecloud.users.model.Users user = userDao.findByUserName(username);
//		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
//
//		return buildUserForAuthentication(user, authorities);
//		
//	}
//	
	
//	public void registerUser(Users user) {
//
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(user.getPassword());
//		user.setPassword(hashedPassword);
//		user.setEnabled(true);
//		user.setCreated(new Date());
//		user.setModified(new Date());
//		user.setLastlogin(new Date());
//		user.assignUserRole(new UserRoles(user,AppConstants.ROLE_USER));
//		
//		userDao.persist(user);
//	}
	
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
		
		
//	vm.getVmname(new vmname());
//		vm.setCreated(new Date());
//		vm.setModified(new Date());
		vmDao.persist(vm);
	}
	
	public void home(Vm vm)
	{
		vm.getVmname();
         vm.getStat();
         vm.getOs();
		
	}
	
	public String Createvm( String vm1, String os1)
	{
		HelloVM.create(vm1, os1);
		return "VM Created";
	}
	
	public ArrayList<VMDto> Showstats()
	{
		ArrayList<VMDto> s=PrintIventory.status();
		
				return s;
	}
	
	public ArrayList<vmstat> sstats()
	{
		ArrayList<vmstat> s=stats.status();
		return s;
	}
	
	public List<Vm> findAllVMs() {
		return vmDao.findAllVMs();
	}
	
	public List<Vm> findAllVMsForUser(String username) {
		return vmDao.findAllVMsForUser(username);
	} 
}
	

