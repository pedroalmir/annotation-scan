/**
 * 
 */
package com.pedroalmir.annotationscan.controller;

import com.pedroalmir.annotationscan.annotation.CheckPermission;
import com.pedroalmir.annotationscan.model.base.UserInterface;

/**
 * @author Pedro Almir
 *
 */
public class SampleController {
	
	@CheckPermission
	public boolean doSomeAction(UserInterface user) {
		return true;
	}
	
	@CheckPermission
	public boolean doSomeAction2(UserInterface user) {
		return true;
	}
	
	@CheckPermission
	public boolean doSomeAction3(UserInterface user) {
		return true;
	}
}
