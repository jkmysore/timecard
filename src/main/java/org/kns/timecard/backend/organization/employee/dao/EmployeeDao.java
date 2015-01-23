package org.kns.timecard.backend.organization.employee.dao;

import java.util.ArrayList;

import org.kns.timecard.exception.EmployeeNotFoundException;
import org.kns.timecard.backend.organization.employee.model.Employee;

/**
 * 
 * @author Bhagya -KNS Technologies Corporation
 * Created On October 22nd ,2014
 *Interface For Employee Dao
 */
public interface EmployeeDao{
	public Integer saveorUpdateEmployeeDetails(Employee employee)throws Exception;
	public Employee getTheEmployeeByEmployeeNo(String employeeNum) throws EmployeeNotFoundException;
	public ArrayList<Employee> getAllEmployeesByOrganizationId(Integer organizationId,Integer page,Integer pageSize) throws EmployeeNotFoundException;
	public Integer getTotalOfEmployeesAndManagers(Integer organizationId) throws Exception;
	public Integer getTotalEmployees(Integer organizationId) throws Exception;
	public Integer getTotalManagers(Integer organizationId) throws Exception;
	public Employee getTheEmployeeByTimecardUserId(Integer userId) throws EmployeeNotFoundException;
}

