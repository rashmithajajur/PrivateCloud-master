package com.privatecloud.users.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.users.model.Params;
import com.privatecloud.users.model.Vm;

@Transactional
public class ParamsDaoImpl implements ParamsDao{
	
Logger log = LoggerFactory.getLogger("ParamsDaoImpl");
	
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Params findByPARAMSID(Integer paramsId) {
		
		List<Params> params = new ArrayList<Params>();
		
		params = getSessionFactory().getCurrentSession().createQuery("from params where paramsId=?")
				.setParameter(0, paramsId).list();
		
		if (params.size() > 0) {
			return params.get(0);
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

	@Override
	public List<Params> findAlllogs(){
		Query query = getSessionFactory().getCurrentSession().createQuery("from Params");
        return query.list();
		
	}
	
	 public void persist(Params transientInstance) {
		 log.debug("persisting Params instance");
	        try {
	            sessionFactory.getCurrentSession().persist(transientInstance);
	            log.debug("persist successful");
	        }
	        catch (RuntimeException re) {
	            log.error("persist failed", re);
	            throw re;
	        }
	    }
}