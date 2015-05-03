package com.privatecloud.manager;

import com.privatecloud.users.dao.ElasticSearchDao;

public class ThresholdAlarmManager extends Thread{
	
	public void run() {
		try {
			System.out.println("Checking and Updating system usage");
			ElasticSearchDao.checkAndUpdateSystemUsage();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Exception while thread sleep");
			//e.printStackTrace();
		}
	}
}
