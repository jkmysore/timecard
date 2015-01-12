package org.kns.timecard.backend.timecarduser.exception;


public class PasswordTokenExpiedException extends Exception{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Password Token Expired";
	}
}
