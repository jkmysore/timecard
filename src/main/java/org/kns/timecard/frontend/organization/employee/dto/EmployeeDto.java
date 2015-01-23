package org.kns.timecard.frontend.organization.employee.dto;

import java.util.Date;



import org.hibernate.validator.constraints.NotEmpty;
import org.kns.timecard.backend.organization.employee.model.Employee;
import org.kns.timecard.frontend.organization.division.dto.DivisionDto;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;

/**
 * Created by Bhagya on September 10, 2014
 * Dto for Employee
 *
 */

public class EmployeeDto{
	private Integer employeeId;
	private TimecardUserDto timecardUser;
	private String employeeNo;
	private OrganizationDto organization;
	private DivisionDto division;
	private Date startDate;
	private Date endDate;
	private Boolean isManager;
	@NotEmpty
	private String confirmPassword;
	
	//Added by Jeevan on 
	
	
	
	
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public TimecardUserDto getTimecardUser() {
		return timecardUser;
	}
	public void setTimecardUser(TimecardUserDto timecardUser) {
		this.timecardUser = timecardUser;
	}
	
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public DivisionDto getDivision() {
		return division;
	}
	public void setDivision(DivisionDto division) {
		this.division = division;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	/*
	 * Added By Bhagya on September 10,2014
	 * Method To follow Employee dto
	 */
	public static EmployeeDto  populateEmployeeDto(Employee employee){
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setEmployeeNo(employee.getEmployeeNo());
		employeeDto.setStartDate(employee.getStartDate());
		employeeDto.setEndDate(employee.getEndDate());
		employeeDto.setIsManager(employee.getIsManager());
		employeeDto.setTimecardUser(TimecardUserDto.populateTimeCardUser(employee.getTimecardUser()));
		if(null!=employee.getOrganization()){
		employeeDto.setOrganization(OrganizationDto.populateOrganizationDto(employee.getOrganization()));
		}
		if(null!=employee.getDivision()){
		employeeDto.setDivision(DivisionDto.populateDivisionDto(employee.getDivision()));
		}
			
		return employeeDto;
	}
	
	
}