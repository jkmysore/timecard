package org.kns.timecard.backend.timecarduser.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author JEEVAN
 *  Created by Jeevan on September 09, 2014
 *  Model for Timecard User roles
 *
 */

@Entity
@Table(name="kns_timecard_roles")
public class Roles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="role_id")
	private Integer roleId;

	
	@Column(name="role_name")
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

}
