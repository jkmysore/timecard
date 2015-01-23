package org.kns.timecard.exception;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 * Exception class to be thrown when no user exists
 */

public class TimecardUserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Timecard User Not Found";
	}
}
