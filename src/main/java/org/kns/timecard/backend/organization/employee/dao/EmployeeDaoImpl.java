package org.kns.timecard.backend.organization.employee.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.kns.timecard.exception.DivisionNotFoundException;
import org.kns.timecard.exception.EmployeeNotFoundException;
import org.kns.timecard.exception.EmployeeNotFoundFilterException;
import org.kns.timecard.backend.organization.division.model.Division;
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
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
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
	/*@SuppressWarnings("unchecked")
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
		
		
	}*/
	/**
	 * Created By bhagya On Feb 27th,2015
	 * @param organizationId
	 * @return division
	 * @throws EmployeeNotFoundException 
	 * @throws EmployeeNotFoundFilterException 
	 * @throws Exception
	 * 
	 * Method to get all Employee Details
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Employee> getEmployeesBasedOnOrganizationId(Integer organizationId,Integer pageNo,Integer pageSize,String sortBy,String searchBy,Boolean ascending,String employeeType) throws EmployeeNotFoundException, EmployeeNotFoundFilterException {
		log.info("inside getDivisionsBasedOnOrganizationId()");
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Employee.class).createAlias("timecardUser", "user").createAlias("timecardUser.timeCardCredentials", "userCredentials");
				criteria.add(Restrictions.eq("organization.organizationId", organizationId));
		if(employeeType!=null && employeeType.contentEquals("managers")  ){
			criteria.add(Restrictions.eq("isManager", true));
		}
		else if(employeeType!=null && employeeType.contentEquals("employees")){
			criteria.add(Restrictions.eq("isManager", false));
			
		}
		if (searchBy != null && !searchBy.isEmpty()) {
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.ilike("user.firstName", searchBy,
					MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("user.lastName", searchBy,
					MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("user.middleName", searchBy,
					MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("userCredentials.username", searchBy,
					MatchMode.ANYWHERE));
			disjunction.add(Restrictions.ilike("userCredentials.email", searchBy,
					MatchMode.ANYWHERE));
			criteria.add(disjunction);
		}
		if(null!=sortBy){
			if(ascending){
				criteria.addOrder(Order.asc(sortBy));
			}
			else{
				criteria.addOrder(Order.desc(sortBy));
			}				
		}
		
		if(null!=pageNo){
			criteria.setFirstResult(pageNo*pageSize);
			criteria.setMaxResults(pageSize);
		}	
		
		ArrayList<Employee> employees=(ArrayList<Employee>) criteria.list();
		
		if(!employees.isEmpty()){
			return employees;
		}
		else if(null!=searchBy){
			throw new EmployeeNotFoundFilterException();
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