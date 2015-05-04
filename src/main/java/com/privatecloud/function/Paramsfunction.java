package com.privatecloud.function;
import java.util.Map;

import com.privatecloud.users.dao.ParameterDao;
import com.privatecloud.users.model.VMlog;
import com.privatecloud.users.model.Alarmstatus;

public class Paramsfunction {
		
		public static VMlog convertMapToVMAlarm(Map<String, Long> map){
			VMlog vmlog = new VMlog();
			vmlog.setCpuThreshold(setDefaultValueIfNull(map.get("CPU")));
			vmlog.setMemoryThreshold(setDefaultValueIfNull(map.get("MEMORY")));
			vmlog.setNetThreshold(setDefaultValueIfNull(map.get("NET")));
			vmlog.setDiskReadThreshold(setDefaultValueIfNull(map.get("DISKREAD")));
			vmlog.setDiskWriteThreshold(setDefaultValueIfNull(map.get("DISKWRITE")));
			return vmlog;
		}
		
		public static boolean setAlarmThresholdValuesForVM(VMlog vmlog){
			String vm = vmlog.getVMName();
			try{
				if(isThresholdValid(vmlog.getCpuThreshold()))
					ParameterDao.setVmPropertyThreshold(vm, "CPU", Integer.parseInt(vmlog.getCpuThreshold().toString()));
				
				if(isThresholdValid(vmlog.getMemoryThreshold()))
					ParameterDao.setVmPropertyThreshold(vm, "MEMORY", Integer.parseInt(vmlog.getMemoryThreshold().toString()));
				
				if(isThresholdValid(vmlog.getNetThreshold()))
					ParameterDao.setVmPropertyThreshold(vm, "NET", Integer.parseInt(vmlog.getNetThreshold().toString()));
				
				if(isThresholdValid(vmlog.getDiskReadThreshold()))
					ParameterDao.setVmPropertyThreshold(vm, "DISKREAD", Integer.parseInt(vmlog.getDiskReadThreshold().toString()));
				
				if(isThresholdValid(vmlog.getDiskWriteThreshold()))
					ParameterDao.setVmPropertyThreshold(vm, "DISKWRITE", Integer.parseInt(vmlog.getDiskWriteThreshold().toString()));
			}
			catch(Exception e){
				return false;
			}
			return true;
		}

		public static Alarmstatus convertMapToAlarmstatus(Map<String, Long> map) {
			// TODO Auto-generated method stub
			Alarmstatus alarmstatus = new Alarmstatus();
			alarmstatus.setCpuAlarm(setDefaultIntValueIfNull(map.get("CPU")));
			alarmstatus.setMemoryAlarm(setDefaultIntValueIfNull(map.get("MEMORY")));
			alarmstatus.setNetAlarm(setDefaultIntValueIfNull(map.get("NET")));
			alarmstatus.setDiskReadAlarm(setDefaultIntValueIfNull(map.get("DISKREAD")));
			alarmstatus.setDiskWriteAlarm(setDefaultIntValueIfNull(map.get("DISKWRITE")));
			return alarmstatus;
		}
		
		public static Long setDefaultValueIfNull(Long value){		
			return (value==0 || value==null)? (long) -1: value;
		}
		
		public static boolean isThresholdValid(Long value){		
			return (value<=0 || value==null )? false: true;
		}
		
		public static int setDefaultIntValueIfNull(Long value){		
			return (value==0 || value==null)? (int) -1: Integer.parseInt(value.toString());
		}

	}


