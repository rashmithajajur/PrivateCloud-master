package com.privatecloud.users.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Users{

    private String username;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private boolean enabled;
    private Date created;
    private Date modified;
    private Date lastlogin;
    private Set<UserRoles> userRole = new HashSet<UserRoles>(0);

   public Users() {
   }

   public Users(String username, String password, boolean enabled) {
	      this.username = username;
	      this.password = password;
	      this.enabled = enabled;
   }
   
   public Users(String username, String fname, String lname, String email, String password, boolean enabled, Date created, Date modified, Date lastlogin) {
      this.username = username;
      this.fname = fname;
      this.lname = lname;
      this.email = email;
      this.password = password;
      this.enabled = enabled;
      this.created = created;
      this.modified = modified;
      this.lastlogin = lastlogin;
   }
  
   public Users(String username, String password, boolean enabled, Set<UserRoles> userRole) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}
   
   public String getUsername() {
       return this.username;
   }
   
   public void setUsername(String username) {
       this.username = username;
   }
   public String getFname() {
       return this.fname;
   }
   
   public void setFname(String fname) {
       this.fname = fname;
   }
   public String getLname() {
       return this.lname;
   }
   
   public void setLname(String lname) {
       this.lname = lname;
   }
   public String getEmail() {
       return this.email;
   }
   
   public void setEmail(String email) {
       this.email = email;
   }
   public String getPassword() {
       return this.password;
   }
   
   public void setPassword(String password) {
       this.password = password;
   }
   public boolean isEnabled() {
       return this.enabled;
   }
   
   public void setEnabled(boolean enabled) {
       this.enabled = enabled;
   }
   public Date getCreated() {
       return this.created;
   }
   
   public void setCreated(Date created) {
       this.created = created;
   }
   public Date getModified() {
       return this.modified;
   }
   
   public void setModified(Date modified) {
       this.modified = modified;
   }
   public Date getLastlogin() {
       return this.lastlogin;
   }
   
   public void setLastlogin(Date lastlogin) {
       this.lastlogin = lastlogin;
   }
   
	public Set<UserRoles> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRoles> userRole) {
		this.userRole = userRole;
	}
	
	public void assignUserRole(UserRoles userRoles) {
		this.userRole.add(userRoles);
	}
}
