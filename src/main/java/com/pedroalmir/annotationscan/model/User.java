/**
 * 
 */
package com.pedroalmir.annotationscan.model;

import java.util.Date;

import com.pedroalmir.annotationscan.model.base.UserInterface;

/**
 * @author Pedro Almir
 *
 */
public class User implements UserInterface {
	
	private String name;
	private String email;
	private String password;
	private Date createdAt;
	private Date updatedAt;
	private Role role;
	
	/**
	 * @param name
	 * @param email
	 * @param password
	 * @param role
	 */
	public User(String name, String email, String password, Role role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdAt = new Date();
		this.updatedAt = this.createdAt;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public Role getRole() {
		return this.role;
	}

}
