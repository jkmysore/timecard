package org.kns.timecard.exception;

public class OrganizationNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Organization Not Found";
	}
}
