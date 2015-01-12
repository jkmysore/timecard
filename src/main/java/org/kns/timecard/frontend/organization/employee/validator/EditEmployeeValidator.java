package org.kns.timecard.frontend.organization.employee.validator;

import org.kns.timecard.frontend.organization.employee.dto.EmployeeDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 *Created On October 22nd,2014
 *Class For Validating The Edit Employee Page
 */

@Component("editEmployeeValidator")
public class EditEmployeeValidator implements Validator{

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return EmployeeDto.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"organization.organizationId","Organization Name should be select","Organization Name should be select");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"employeeNo","Employee Id cannot be empty","Employee Id cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"division.divisionId","Division Name should be select","Division Name should be select");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.firstName","First Name cannot be empty","First Name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.lastName","Last Name cannot be empty","Last Name  cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.timeCardCredentials.username","Username cannot be empty","Username cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.timeCardCredentials.email","Email cannot be empty","Email cannot be empty");
			
	}

}
