package org.kns.timecard.backend.organization.division.exception;

public class DivisionNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {		
		return "Division Not Found Exception";
	}
	
}
