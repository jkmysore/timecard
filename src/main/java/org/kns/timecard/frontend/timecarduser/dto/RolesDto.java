package org.kns.timecard.frontend.timecarduser.dto;

import org.kns.timecard.backend.timecarduser.model.Roles;

/**
 * Created by Bhagya on September 10, 2014
 * Dto for	Timecard User Roles
 *
 */

public class RolesDto{
	
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
	
	/*Added by bhagya on September 10,2014
	 * Method To follow Roles Dto
	 */
	public static RolesDto populateRolesDto(Roles roles){
		RolesDto rolesDto=new RolesDto();
		rolesDto.setRoleId(roles.getRoleId());
		rolesDto.setRoleName(roles.getRoleName());
		return rolesDto;
	}
}