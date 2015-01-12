package org.kns.timecard.backend.timecard.organization.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Created by Jeevan on June 16, 2014
 * Model for Divisions (Departments)
 */
@Entity
@Table(name="kns_timecard_division")
public class Division implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="division_id")
	private Integer divisionId;
	
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	
	@Column(name="division_name")
	private String divisionName;


	public Integer getDivisionId() {
		return divisionId;
	}


	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	public String getDivisionName() {
		return divisionName;
	}


	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	
	
	
}
