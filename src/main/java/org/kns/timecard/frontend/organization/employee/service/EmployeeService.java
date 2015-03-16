package org.kns.timecard.frontend.organization.employee.service;

import java.util.ArrayList;

import org.kns.timecard.exception.EmployeeNotFoundException;
import org.kns.timecard.exception.EmployeeNotFoundFilterException;
import org.kns.timecard.frontend.organization.employee.dto.EmployeeDto;

/**
 * 
 * @author Bhagya -KNS Technologies Corporation
 * Created On October 22nd,2014
 * Service For Employee
 *
 */

public interface EmployeeService{
	 public Integer savingAddedEmployeeDetails(EmployeeDto employeeDto,Integer divisionId,Integer organizationId) throws Exception;
	 public EmployeeDto getTheEmployeeByEmployeeNo(String employeeNo) throws EmployeeNotFoundException;
	 public ArrayList<EmployeeDto> getTheEmployeesAndManagers(Integer organizationId,String employeeType,Integer pageNo,Integer pageSize,String sortBy,String searchBy,Boolean ascending) throws EmployeeNotFoundException, EmployeeNotFoundFilterException;
	 public Integer getTotalResultsOfEmployeesAndManagers(Integer organizationId) throws Exception;
	 public Integer getTotalResultsOfEmployees(Integer organizationId) throws Exception;
	 public Integer getTotalResultsOfManagers(Integer organizationId) throws Exception;
	 public EmployeeDto getTheEmployeeByTimecardUserId(Integer userId) throws EmployeeNotFoundException;
	 public Integer saveEmployeeAsManager(EmployeeDto employeeDto) throws Exception;
	 public Integer savingAddedManagerDetails(EmployeeDto employeeDto,Integer divisionId,Integer organizationId) throws Exception;
	 public Integer removeOrganizationManager(EmployeeDto employeeDto) throws Exception;
	 public Integer savingUpdatedEmployeeDetails(EmployeeDto employeeDto,Integer divisionId,Integer organizationId) throws Exception;
}