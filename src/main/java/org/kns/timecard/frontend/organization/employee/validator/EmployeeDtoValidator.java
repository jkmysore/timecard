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
 *Class For Validating The Employee Dto
 */

@Component("employeeDtoValidator")
public class EmployeeDtoValidator implements Validator{

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return EmployeeDto.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		EmployeeDto eDto=(EmployeeDto) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"employeeNo","Employee Id cannot be empty","Employee Id cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"division.divisionId","Division Name should be select","Division Name should be select");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.firstName","First Name cannot be empty","First Name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.lastName","Last Name cannot be empty","Last Name  cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.timeCardCredentials.username","Username cannot be empty","Username cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.timeCardCredentials.email","Email cannot be empty","Email cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timecardUser.timeCardCredentials.password","Password cannot be empty","Password cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","Confirm Password cannot be empty","Confirm Password cannot be empty");
		if(!eDto.getTimecardUser().getTimeCardCredentials().getPassword().equals(eDto.getConfirmPassword())){
			errors.rejectValue("confirmPassword", "Password and Confirm Password should match","Password and Confirm Password should match");
			errors.reject("confirmPassword", "Password and Confirm Password should match");
		}		
	}

}
