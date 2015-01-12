package org.kns.timecard.frontend.timecarduser.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 *
 *	Created on October 21st, 2014
 *	 Dto for Change Password
 *
 *
 */

public class ChangePasswordDto {

	@NotBlank
	@NotNull
	@Size(min=8,max=20)
	private String oldPassword;
	
	@NotBlank
	@NotNull
	@Size(min=8,max=20)
	private String newPassword;
	
	@NotBlank
	@NotNull
	@Size(min=8,max=20)
	private String confirmPassword;
	private Integer userId;
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	
}
