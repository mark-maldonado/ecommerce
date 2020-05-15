//CREATED BY SEYIT ARAR

package de.thi.shop.user.beans;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private boolean isAdmin = false;
	
	
	public UserBean()
	{
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
