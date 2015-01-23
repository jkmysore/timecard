package org.kns.timecard.exception;
/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 *Created on June 19, 2014
 *Class for Rolesa
 */
public class RoleNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		
		return "Roles Not Found";
	}
}
