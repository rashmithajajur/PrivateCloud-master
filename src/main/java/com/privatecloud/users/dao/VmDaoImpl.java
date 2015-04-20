package com.privatecloud.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.users.model.Vm;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class VmDaoImpl implements VmDao {

	Logger log = LoggerFactory.getLogger("VmDaoImpl");
	
	@Autowired
	private SessionFactory sessionFactory;

//	public Vm findById(String Id)
//	{
//		
//	}
	
//	@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	public Vm findByVmName(String vmname) {

		List<Vm> vms = new ArrayList<Vm>();
		

		vms = getSessionFactory().getCurrentSession().createQuery("select * from Vm ")
				.setParameter(0, vmname).list();
		
		if (vms.size() > 0) {
			return vms.get(0);
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

	@Transactional
	 public void persist(Vm transientInstance) {
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

	
	@Override
	public Vm findById(String Id) {
		// TODO Auto-generated method stub
		
		List<Vm> id = new ArrayList<Vm>();

		id = getSessionFactory().getCurrentSession().createQuery("from Vm where =?")
				.setParameter(0, Id).list();

		if (id.size() > 0) {
			return id.get(0);
		} else {
			return null;
		}
		
	}
	
	@Override
	public List<Vm> findAllVMs(){
		Query query = getSessionFactory().getCurrentSession().createQuery("from Vm");
        return query.list();
		
	}
	
	@Override
	public List<Vm> findAllVMsForUser(String username){
		Query query = getSessionFactory().getCurrentSession().createQuery("from Vm where username = ?")
				.setParameter(0, username);
        return query.list();
		
	}
}