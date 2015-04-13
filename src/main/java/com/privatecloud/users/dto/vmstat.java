package com.privatecloud.users.dto;

public class vmstat {

	String vmName;
	String cpu;
	String mem;
	
	public String getVmName() {
		return vmName;
	}
	public void setVmName(String vmName) {
		this.vmName = vmName;
	}
	public String getcpu() {
		return cpu;
	}
	public void setcpu(String cpu) {
		this.cpu = cpu;
	}
	public String getmem() {
		return mem;
	}
	public void setmem(String mem) {
		this.mem = mem;
	}	
}
