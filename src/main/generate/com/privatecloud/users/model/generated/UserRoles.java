package com.privatecloud.users.model.generated;

// Generated Apr 19, 2015 10:24:33 PM by Hibernate Tools 3.4.0.CR1

/**
 * UserRoles generated by hbm2java
 */
public class UserRoles implements java.io.Serializable {

	private Integer userRoleId;
	private String username;
	private String role;

	public UserRoles() {
	}

	public UserRoles(String username, String role) {
		this.username = username;
		this.role = role;
	}

	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}