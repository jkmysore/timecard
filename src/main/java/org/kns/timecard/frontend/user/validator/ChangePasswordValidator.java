package org.kns.timecard.frontend.user.validator;

import org.kns.timecard.frontend.user.dto.ChangePasswordDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 */

@Component("changePasswordValidator")
public class ChangePasswordValidator implements Validator{

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return ChangePasswordDto.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ChangePasswordDto cpDto=(ChangePasswordDto) obj;
		System.out.println("INSIDE VALIDATOR");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"oldPassword","Password cannot be empty","Password cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "New Password cannot be empty","New Password cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","Confirm Password cannot be empty","Confirm Password cannot be empty");
		if(!cpDto.getNewPassword().equals(cpDto.getConfirmPassword())){
			errors.rejectValue("confirmPassword", "Password and Confirm Password should match","Password and Confirm Password should match");
			errors.reject("confirmPassword", "Password and Confirm Password should match");
		}		
	}

}
