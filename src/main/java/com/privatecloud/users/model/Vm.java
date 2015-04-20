package com.privatecloud.users.model;


public class Vm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7526564192020617021L;
	private Integer id;
	private String vmname;
	private String username;
	private String os;
	private Boolean isPowerOn;
	
	public Boolean getIsPowerOn() {
		return isPowerOn;
	}

	public void setPowerOn(Boolean isPowerOn) {
		this.isPowerOn = isPowerOn;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Vm() {
	}

	public Vm(String vmname, String username) {
		this.vmname = vmname;
		this.username = username;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVmname() {
		return this.vmname;
	}

	public void setVmname(String vmname) {
		this.vmname = vmname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
