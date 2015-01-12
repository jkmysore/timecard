package org.kns.timecard.frontend.user.dto;

/**
 * @author JEEVAN
 * 
 * DTO for TimeCard User
 */


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.kns.timecard.backend.user.model.Roles;
import org.kns.timecard.backend.user.model.TimeCardUser;
import org.springframework.web.multipart.MultipartFile;

public class TimecardUserDto {
	
	private Integer userId;
	private String username;
	private String password;
	private String email;	
	private String firstName;
	private String middleName;
	private String lastName;
	private TimecardUserDto createdBy;
	private TimecardUserDto modifiedBy;	
	private Date createdDate;
	private Date modifiedDate;
	private Set<RolesDto> role=new HashSet<RolesDto>();		
	private Boolean isEnabled;	
	private Boolean isActive;
	private Date accountExpiryDate;
	//added on June 24, 2014
	private String passwordToken;
	private String profilePic;
	private MultipartFile photo;
	private Date tokenExpiryDate;
	private Boolean isFirstLogin;
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public TimecardUserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(TimecardUserDto createdBy) {
		this.createdBy = createdBy;
	}
	public TimecardUserDto getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(TimecardUserDto modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Set<RolesDto> getRole() {
		return role;
	}
	public void setRole(Set<RolesDto> role) {
		this.role = role;
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
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	
	
	
	public Date getTokenExpiryDate() {
		return tokenExpiryDate;
	}
	public void setTokenExpiryDate(Date tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}
	
	
	
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}
	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}
	public static TimecardUserDto populateTimeCardUser(TimeCardUser user){
		TimecardUserDto userDto=new TimecardUserDto();
		if(null!=user.getAccountExpiryDate()){
			userDto.setAccountExpiryDate(user.getAccountExpiryDate());
		}
		if(null!=user.getCreatedBy()){
			userDto.setCreatedBy(TimecardUserDto.populateTimeCardUser(user.getCreatedBy()));
		}
		if(null!=user.getCreatedDate()){
			userDto.setCreatedDate(user.getCreatedDate());
		}
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setIsActive(user.getIsActive());
		userDto.setIsEnabled(user.getIsEnabled());
		userDto.setLastName(user.getLastName());
		userDto.setMiddleName(user.getMiddleName());
		if(null!=user.getModifiedBy()){
			userDto.setModifiedBy(TimecardUserDto.populateTimeCardUser(user.getModifiedBy()));
		}
		if(null!=user.getModifiedDate()){
			userDto.setModifiedDate(user.getModifiedDate());
		}
		userDto.setPassword(user.getPassword());
		Set<RolesDto> roleDtos=new HashSet<RolesDto>();
		for(Roles role:user.getRole()){
			RolesDto roleDto=RolesDto.populateRolesDto(role);
			roleDtos.add(roleDto);
		}
		userDto.setRole(roleDtos);
		userDto.setUserId(user.getUserId());
		userDto.setUsername(user.getUsername());
		userDto.setPasswordToken(user.getPasswordToken());
		userDto.setProfilePic(user.getProfilePic());
		if(null!=user.getTokenExpiryDate()){
			userDto.setTokenExpiryDate(user.getTokenExpiryDate());
		}
		if(null!=user.getIsFirstLogin()){
			userDto.setIsFirstLogin(user.getIsFirstLogin());
		}
		return userDto;
	}
	
	
}
