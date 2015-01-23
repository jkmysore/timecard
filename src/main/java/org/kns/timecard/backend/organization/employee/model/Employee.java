package org.kns.timecard.backend.organization.employee.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.kns.timecard.backend.organization.division.model.Division;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;

/**
 * 
 * @author JEEVAN
 * 
 * Created by Jeevan on September 10, 2014
 * Model for Employee
 *
 */
@Entity
@Table(name="kns_timecard_employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="employee_id")
	private Integer employeeId;
	
	@OneToOne
	@JoinColumn(name="timecard_user_id")
	private TimecardUser timecardUser;
	
	@Column(name="employee_no")
	private String employeeNo;
	
	@Fetch(FetchMode.JOIN)
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="division_id")
	private Division division;
	
	
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name="is_manager")
	private Boolean isManager;
	
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	private TimecardUser manager;

	
	
	
	
	
	public TimecardUser getManager() {
		return manager;
	}

	public void setManager(TimecardUser manager) {
		this.manager = manager;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public TimecardUser getTimecardUser() {
		return timecardUser;
	}

	public void setTimecardUser(TimecardUser timecardUser) {
		this.timecardUser = timecardUser;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;
	}

	
	
	
	
	
}
