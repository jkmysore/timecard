package org.kns.timecard.frontend.user.dto;

import org.kns.timecard.backend.user.model.Roles;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *	
 *	Created on June 120, 2014
 *
 *	DTO FOR ROLES
 */

public class RolesDto {

	private Integer roleId;
	private String roleName;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
	/*
	 *		 Method to follow Roles Dto 
	 */
	public static RolesDto populateRolesDto(Roles roles){
		RolesDto rolesDto=new RolesDto();
		rolesDto.setRoleId(roles.getRoleId());
		rolesDto.setRoleName(roles.getRoleName());
		return rolesDto;
	}
	
	
	
	
	
	
}
