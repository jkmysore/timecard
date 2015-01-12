package org.kns.timecard.frontend.organization.employee.dto;

import java.util.Date;

/**
 * Created by Bhagya on September 10, 2014
 * Dto for Employee Interface
 *
 */

public class EmployeeInterfaceDto{
	private Integer id;
	private Integer organizationId;
	private Integer employeeNo;
	private Integer divisionNo;
	private String lastName;
	private String firstName;
	private String email;
	private Date dateofBirth;
	private Date startDate;
	private Date endDate;
	private Boolean isUploaded;
	private String errorMessage;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(Integer employeeNo) {
		this.employeeNo = employeeNo;
	}
	public Integer getDivisionNo() {
		return divisionNo;
	}
	public void setDivisionNo(Integer divisionNo) {
		this.divisionNo = divisionNo;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
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
	public Boolean getIsUploaded() {
		return isUploaded;
	}
	public void setIsUploaded(Boolean isUploaded) {
		this.isUploaded = isUploaded;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}