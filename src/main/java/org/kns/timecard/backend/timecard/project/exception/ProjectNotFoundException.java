package org.kns.timecard.backend.timecard.project.exception;

/*
 * Exception class for Project Not Found Condition
 */

public class ProjectNotFoundException extends Exception{

	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {		
		return "Project Not Found";
	}
}
