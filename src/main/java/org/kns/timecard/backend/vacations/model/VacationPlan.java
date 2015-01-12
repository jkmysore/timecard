package org.kns.timecard.backend.vacations.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kns.timecard.backend.timecard.organization.model.Employee;
import org.kns.timecard.backend.timecard.organization.model.Organization;

/*
 *  Created by Jeevan on June 18, 2014
 *   MEthod for Vacation Plans
 */

@Entity
@Table(name="kns_timecard_vacation_plan")
public class VacationPlan implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="vacation_plan_id")
	private Integer vacationPlanId;
	
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Column(name="vacation_start_date")
	private Date startDate;
	
	@Column(name="vacation_end_date")
	private Date endDate;
	
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="vacation_approval_status")
	private Boolean approvalStatus;

	public Integer getVacationPlanId() {
		return vacationPlanId;
	}

	public void setVacationPlanId(Integer vacationPlanId) {
		this.vacationPlanId = vacationPlanId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
}
