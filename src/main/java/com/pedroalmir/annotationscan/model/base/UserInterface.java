/**
 * 
 */
package com.pedroalmir.annotationscan.model.base;

import java.util.Date;

import com.pedroalmir.annotationscan.model.Role;

/**
 * @author Pedro Almir
 *
 */
public interface UserInterface {
	String getName();
	String getEmail();
	String getPassword();
	Date getCreatedAt();
	Date getUpdatedAt();
	Role getRole();
}
