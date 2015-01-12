package org.kns.timecard.backend.timecarduser.exception;
/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *	
 *	Created by Jeevan on July 02, 2014
 *
 */

public class PasswordNotMatchedException extends Exception{

	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Password do not match";
	}
}
