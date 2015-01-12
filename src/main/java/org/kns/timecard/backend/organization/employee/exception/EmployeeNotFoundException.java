package org.kns.timecard.backend.organization.employee.exception;

public class EmployeeNotFoundException extends Exception {


	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "Employee Not Found Exception";
	}	
	
}
