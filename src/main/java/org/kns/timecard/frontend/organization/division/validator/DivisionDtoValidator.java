package org.kns.timecard.frontend.organization.division.validator;

import org.kns.timecard.frontend.organization.division.dto.DivisionDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * 
 * @author Bhagya-- KNS Technologies Corporation
 * 
 * Created On October 22nd,2014
 * 
 * Class for Validating The Division Dto
 *
 */

@Component("divisionDtoValidator")
public class DivisionDtoValidator implements Validator{

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return DivisionDto.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"organization.organizationName","Organization Name Should Be Select ","Organization Name Should Be Select");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"divisionNo","Division Id cannot be empty","Division Id cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"divisionName","Division Name cannot be empty","Division Name  cannot be empty");
			
	}

}
