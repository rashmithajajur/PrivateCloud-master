package com.privatecloud.users.model.generated;

// Generated Apr 19, 2015 10:24:33 PM by Hibernate Tools 3.4.0.CR1

/**
 * Vm generated by hbm2java
 */
public class Vm implements java.io.Serializable {

	private Integer id;
	private String vmname;
	private String username;

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