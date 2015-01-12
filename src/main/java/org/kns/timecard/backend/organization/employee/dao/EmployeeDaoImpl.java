package org.kns.timecard.backend.organization.employee.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.kns.timecard.backend.organization.division.exception.DivisionNotFoundException;
import org.kns.timecard.backend.organization.division.model.Division;
import org.kns.timecard.backend.organization.employee.exception.EmployeeNotFoundException;
import org.kns.timecard.backend.organization.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Bhagya -KNS Technologies Corporation
 * Created On October 22nd,2014
 * Class For Implementation Of Employee Dao
 *
 */
@Transactional
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao{
	private static Logger log=Logger.getLogger(EmployeeDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Created By Bhagya On October 27th,2014
	 * @param employee object
	 * @return employeeId for confirmation of saving
	 * @throws Exception
	 * 
	 * Method For Save Or Update employee Details
	 */
	public Integer saveorUpdateEmployeeDetails(Employee employee)throws Exception{
		log.info("inside saveorUpdateEmployeeDetails");
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
		sessionFactory.getCurrentSession().flush();
		return employee.getEmployeeId();
	}
	
	/**
	 * Created By Bhagya On October 27th,2014
	 * @param employeeNum
	 * @return employee
	 * @throws EmployeeNotFoundException
	 * 
	 * Method For Get The Employee By Employee No
	 */
	@SuppressWarnings("unchecked")
	public Employee getTheEmployeeByEmployeeNo(String employeeNum) throws EmployeeNotFoundException{
		log.info("inside getEmployeeByEmployeenum()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.add(Restrictions.eq("employeeNo", employeeNum));
		
		ArrayList<Employee> employees=(ArrayList<Employee>) criteria.list();
		
		if(!employees.isEmpty()){
			return employees.get(0);
			
		}
		else{
			throw new EmployeeNotFoundException();
		}
	}
	
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param organizationId,page,pageSize
	 * @return employees
	 * @throws EmployeeNotFoundException
	 * 
	 * Method For get All Employees Based On OrganizationId
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Employee> getAllEmployeesByOrganizationId(Integer organizationId,Integer page,Integer pageSize) throws EmployeeNotFoundException{
		log.info("inside getAllDivisionsFromDB()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.add(Restrictions.eq("organization.organizationId", organizationId));
		
		if(null!=page && null!=pageSize){
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult(page*pageSize);
		}
		criteria.addOrder(Order.desc("employeeId"));
		ArrayList<Employee> employees=(ArrayList<Employee>) criteria.list();
		
		if(!employees.isEmpty()){
			return employees;
		}
		else{
			throw new EmployeeNotFoundException();
		}
		
		
	}
	
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param organizationId
	 * @return totalResults..count of all employees i.e.. both employees and managers
	 * @throws Exception
	 * 
	 * Method For get the count of all employees i.e.. both employees and managers
	 */
	
	public Integer getTotalOfEmployeesAndManagers(Integer organizationId) throws Exception{
		log.info("inside getTotalOfEmployeesAndManagers()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.add(Restrictions.eq("organization.organizationId",organizationId));
		criteria.setProjection(Projections.count("employeeId")).list();
		Integer totalResults=Integer.valueOf (criteria.list().get(0).toString());
		return totalResults;
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param organizationId
	 * @return totalResults..count value of Employess
	 * @throws Exception
	 * 
	 * Method for get the Total count Of Employees
	 */
	public Integer getTotalEmployees(Integer organizationId) throws Exception{
		log.info("inside getTotalEmployees()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.add(Restrictions.and(Restrictions.eq("organization.organizationId", organizationId), Restrictions.eq("isManager", false)));
		criteria.setProjection(Projections.count("isManager")).list();
		Integer totalResults=Integer.valueOf (criteria.list().get(0).toString());
		return totalResults;
	}
	/**
	 * Craeted By Bhagya On October 28th,2014
	 * @param organizationId
	 * @return totalResults..Count value of Managers
	 * @throws Exception
	 * 
	 * Method for get the total count of Managers
	 */
	public Integer getTotalManagers(Integer organizationId) throws Exception{
		log.info("inside getTotalManagers()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.add(Restrictions.and(Restrictions.eq("organization.organizationId", organizationId), Restrictions.eq("isManager", true)));
		criteria.setProjection(Projections.count("isManager")).list();
		Integer totalResults=Integer.valueOf (criteria.list().get(0).toString());
		return totalResults;
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param userId
	 * @return employee
	 * @throws EmployeeNotFoundException
	 * 
	 * Method For getting the employee based on timecarduserid
	 */
	@SuppressWarnings("unchecked")
	public Employee getTheEmployeeByTimecardUserId(Integer userId) throws EmployeeNotFoundException{
		log.info("inside getTheEmployeeByTimecardUserId()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.add(Restrictions.eq("timecardUser.userId", userId));
		ArrayList<Employee> employees=(ArrayList<Employee>) criteria.list();
		
		if(!employees.isEmpty()){
			return employees.get(0);
			
		}
		else{
			throw new EmployeeNotFoundException();
		}
	}
}