package com.privatecloud.users.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.privatecloud.users.model.Alarm;
import com.privatecloud.users.model.Vm;

@Transactional
public class AlarmDaoImpl implements AlarmDao {
	
Logger log = LoggerFactory.getLogger("UserDaoImpl");
	
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Alarm findByVMID(Integer vm_id) {
		
		List<Alarm> alarm = new ArrayList<Alarm>();
		
		alarm = getSessionFactory().getCurrentSession().createQuery("from alarm where vm_id=?")
				.setParameter(0, vm_id).list();
		
		if (alarm.size() > 0) {
			return alarm.get(0);
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
//	public List<Alarm> findAlllogs(){
//		Query query = getSessionFactory().getCurrentSession().createQuery("from alarm where username = ?")
//				.setParameter(0, username);
//        return query.list();
//		
//	}
//	
	 public void persist(Alarm transientInstance) {
		 log.debug("persisting Alarm instance");
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