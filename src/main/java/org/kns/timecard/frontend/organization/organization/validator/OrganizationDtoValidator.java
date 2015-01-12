package org.kns.timecard.frontend.organization.organization.validator;


import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 * 
 * Created By Bhagya on 15-oct-2014
 *  Validator for organization
 *
 */

@Component("organizationDtoValidator")
public class OrganizationDtoValidator implements Validator{

	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return OrganizationDto.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"organizationName","Organization Name Should Not Be Empty","Organization Name Should Not Be Empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"organizationShortName","Organization Short Name Should Not Be Empty","Organization Short Name Should Not Be Empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"siteAdmin.timeCardCredentials.username","SiteAdmin Username cannot be empty","SiteAdmin Username cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"siteAdmin.timeCardCredentials.email","SiteAdmin Email cannot be empty","SiteAdmin Email cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"siteAdmin.timeCardCredentials.password","SiteAdmin Password cannot be empty","SiteAdmin Password cannot be empty");
		
	}

}
