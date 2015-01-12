package org.kns.timecard.frontend.timecarduser.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.kns.timecard.backend.timecarduser.model.Roles;
import org.kns.timecard.backend.timecarduser.model.TimeCardUserCredentials;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;

/**
 * Created by Bhagya on September 10, 2014
 * Dto for Time card User credentials
 *
 */

public class TimeCardUserCredentialsDto{
	private Integer id;
	@NotNull
	@NotBlank
	private String username;
	@NotNull
	@NotBlank
	private String password;
	@NotNull
	@NotBlank
	private String email;
	private Set<RolesDto> role=new HashSet<RolesDto>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Set<RolesDto> getRole() {
		return role;
	}
	public void setRole(Set<RolesDto> role) {
		this.role = role;
	}
	
	/*Added by bhagya on september 10,2014
	 * Method To follow timecarduser credentials Dto
	 */
	public static TimeCardUserCredentialsDto populateTimeCardUserCredentials(TimeCardUserCredentials user){
		TimeCardUserCredentialsDto userDto=new TimeCardUserCredentialsDto();
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setUsername(user.getUsername());
		Set<RolesDto> roleDtos=new HashSet<RolesDto>();
		for(Roles role:user.getRole()){
			RolesDto roleDto=RolesDto.populateRolesDto(role);
			roleDtos.add(roleDto);
		}
		userDto.setRole(roleDtos);
		return userDto;
	}
}