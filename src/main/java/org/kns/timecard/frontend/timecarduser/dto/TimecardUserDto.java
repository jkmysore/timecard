package org.kns.timecard.frontend.timecarduser.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.kns.timecard.backend.timecarduser.model.Roles;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;

/**
 * Created by Bhagya on September 10, 2014
 * Dto for Time card User
 *
 */

public class TimecardUserDto{
	private Integer userId;
	private TimeCardUserCredentialsDto timeCardCredentials;
	private String firstName;
	private String lastName;
	private String middleName;
	private TimecardUserDto createdBy;
	private Date createdDate;
	private TimecardUserDto modifiedBy;
	private Date modifedDate;
	private Date accountExpiryDate;
	private String passwordToken;
	private String profilePic;
	private Boolean isFirstLogin;
	private Date dateofBirth;
	private Boolean isEnabled;
	private Boolean isActive;
	private Boolean isLocked;
	private Date tokenExpiryDate;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public TimeCardUserCredentialsDto getTimeCardCredentials() {
		return timeCardCredentials;
	}
	public void setTimeCardCredentials(
			TimeCardUserCredentialsDto timeCardCredentials) {
		this.timeCardCredentials = timeCardCredentials;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public TimecardUserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(TimecardUserDto createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public TimecardUserDto getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(TimecardUserDto modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifedDate() {
		return modifedDate;
	}
	public void setModifedDate(Date modifedDate) {
		this.modifedDate = modifedDate;
	}
	public Date getAccountExpiryDate() {
		return accountExpiryDate;
	}
	public void setAccountExpiryDate(Date accountExpiryDate) {
		this.accountExpiryDate = accountExpiryDate;
	}
	public String getPasswordToken() {
		return passwordToken;
	}
	public void setPasswordToken(String passwordToken) {
		this.passwordToken = passwordToken;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}
	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	public Date getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
	
	public Date getTokenExpiryDate() {
		return tokenExpiryDate;
	}
	public void setTokenExpiryDate(Date tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}
		
	
	/*
	 * Added By Bhagya on September 10,2014
	 * Method To follow TimecardUser dto
	 */
	public static TimecardUserDto populateTimeCardUser(TimecardUser user){
		TimecardUserDto userDto=new TimecardUserDto();
		userDto.setUserId(user.getUserId());
		userDto.setTimeCardCredentials(TimeCardUserCredentialsDto.populateTimeCardUserCredentials(user.getTimeCardCredentials()));
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setMiddleName(user.getMiddleName());
		if(null!=user.getCreatedBy()){
			userDto.setCreatedBy(TimecardUserDto.getTimecardUser(user.getCreatedBy()));
		}
		if(null!=user.getCreatedDate()){
			userDto.setCreatedDate(user.getCreatedDate());
		}
				
		if(null!=user.getModifiedBy()){
			userDto.setModifiedBy(TimecardUserDto.getTimecardUser(user.getModifiedBy()));
		}
		if(null!=user.getModifedDate()){
			userDto.setModifedDate(user.getModifedDate());
		}
		if(null!=user.getAccountExpiryDate()){
			userDto.setAccountExpiryDate(user.getAccountExpiryDate());
		}		
		userDto.setPasswordToken(user.getPasswordToken());
		userDto.setProfilePic(user.getProfilePic());
		if(null!=user.getIsFirstLogin()){
			userDto.setIsFirstLogin(user.getIsFirstLogin());
		}		
		userDto.setDateofBirth(user.getDateofBirth());
		userDto.setIsActive(user.getIsActive());
		userDto.setIsEnabled(user.getIsEnabled());
		userDto.setIsLocked(user.getIsLocked());
		if(null!=user.getTokenExpiryDate()){
			userDto.setTokenExpiryDate(user.getTokenExpiryDate());
		}
		
		return userDto;
	}
	
	
	
	private static TimecardUserDto getTimecardUser(TimecardUser timecardUser){
		TimecardUserDto timecardUserDto=new TimecardUserDto();
		timecardUserDto.setUserId(timecardUser.getUserId());
		return timecardUserDto;
	}
}