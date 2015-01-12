package org.kns.timecard.frontend.organization.employee.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.organization.division.dao.DivisionDao;
import org.kns.timecard.backend.organization.division.model.Division;
import org.kns.timecard.backend.organization.employee.dao.EmployeeDao;
import org.kns.timecard.backend.organization.employee.exception.EmployeeNotFoundException;
import org.kns.timecard.backend.organization.employee.model.Employee;
import org.kns.timecard.backend.organization.organization.dao.OrganizationDao;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.timecarduser.dao.UserDao;
import org.kns.timecard.backend.timecarduser.model.TimeCardUserCredentials;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.frontend.organization.employee.dto.EmployeeDto;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Bhagya -KNS Technologies Corporation
 * Created On October 22nd,2014
 *Class For Implemeting The Employee Interface or service
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	private static Logger log=Logger.getLogger(EmployeeServiceImpl.class);
	
	@Resource(name="organizationDao")
	private OrganizationDao organizationDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="divisionDao")
	private DivisionDao divisionDao;
	
	@Resource(name="employeeDao")
	private EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Created By Bhagya On October 27th,2014
	 * @param employeeDto,divisionId,organizationId
	 * @return savedResult..confirmation Result for saving the added employee details
	 * @throws Exception
	 * 
	 * Method For Saving the Added Employees Details
	 */
	public Integer savingAddedEmployeeDetails(EmployeeDto employeeDto,Integer divisionId,Integer organizationId) throws Exception{
		log.info("inside savingAddedEmployeeDetails");
		Employee employee=new Employee();
		employee.setEmployeeNo(employeeDto.getEmployeeNo());
		employee.setStartDate(employeeDto.getStartDate());
		TimecardUser user=new TimecardUser();
		user.setDateofBirth(employeeDto.getTimecardUser().getDateofBirth());
		user.setFirstName(employeeDto.getTimecardUser().getFirstName());
		user.setLastName(employeeDto.getTimecardUser().getLastName());
		TimeCardUserCredentials timecardcredentials=new TimeCardUserCredentials();
		timecardcredentials.setEmail(employeeDto.getTimecardUser().getTimeCardCredentials().getEmail());
		String encryPassword=passwordEncoder.encode(employeeDto.getTimecardUser().getTimeCardCredentials().getPassword());
		timecardcredentials.setPassword(encryPassword);
		timecardcredentials.setUsername(employeeDto.getTimecardUser().getTimeCardCredentials().getUsername());
		user.setTimeCardCredentials(timecardcredentials);
		this.userDao.saveOrUpdateTimecardCredentials(timecardcredentials);
		this.userDao.saveOrUpdateTimecardUser(user);
		employee.setTimecardUser(user);
		employee.setIsManager(false);
		Division division=this.divisionDao.getDivisionByDivisionId(divisionId);
		employee.setDivision(division);
		Organization organization=this.organizationDao.getOrganizationById(organizationId);
		employee.setOrganization(organization);
		Integer savedResult=this.employeeDao.saveorUpdateEmployeeDetails(employee);
		return savedResult;
	}
	/**
	 * Created By Bhagya on October 27th,2014
	 * @param employeeNo
	 * @return employeeDto
	 * @throws EmployeeNotFoundException
	 * 
	 * Method For Get the Employee By Employee No
	 */
	public EmployeeDto getTheEmployeeByEmployeeNo(String employeeNo) throws EmployeeNotFoundException{
		log.info("inside getTheEmployeeByEmployeeNo()");
		Employee employee=this.employeeDao.getTheEmployeeByEmployeeNo(employeeNo);
		EmployeeDto employeeDto=EmployeeDto.populateEmployeeDto(employee);
		return employeeDto;
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param organizationId,page,pageSize
	 * @return employeeDto
	 * @throws EmployeeNotFoundException
	 * 
	 * Method For Get the Employees and Managers
	 */
	public ArrayList<EmployeeDto> getTheEmployeesAndManagers(Integer organizationId,Integer page,Integer pageSize,String employeeType) throws EmployeeNotFoundException{
		log.info("inside getemployeesAndManagers()");
		ArrayList<Employee> employees=this.employeeDao.getAllEmployeesByOrganizationId(organizationId,page,pageSize);
		ArrayList<EmployeeDto> employeeDtos=new ArrayList<EmployeeDto>();
		for(Employee employee:employees ){
			if(employeeType==null ||employeeType.contentEquals("allemployees")||employeeType.trim().length()<=0 ){
				EmployeeDto employeeDto=new EmployeeDto();
				employeeDto.setEmployeeNo(employee.getEmployeeNo());
				employeeDto.setTimecardUser(TimecardUserDto.populateTimeCardUser(employee.getTimecardUser()));
				employeeDto.setStartDate(employee.getStartDate());
				employeeDto.setIsManager(employee.getIsManager());
			
				employeeDtos.add(employeeDto);
			}
			
			else if(null!=employeeType){
				if(employeeType.contentEquals("managers")){
					if(employee.getIsManager()==true){
					EmployeeDto employeeDto=new EmployeeDto();
					employeeDto.setEmployeeNo(employee.getEmployeeNo());
					employeeDto.setTimecardUser(TimecardUserDto.populateTimeCardUser(employee.getTimecardUser()));
					employeeDto.setStartDate(employee.getStartDate());
					employeeDto.setIsManager(employee.getIsManager());
					
					employeeDtos.add(employeeDto);
					
				}
			}
				else{
				if(employee.getIsManager()==false){
					EmployeeDto employeeDto=new EmployeeDto();
					employeeDto.setEmployeeNo(employee.getEmployeeNo());
					employeeDto.setTimecardUser(TimecardUserDto.populateTimeCardUser(employee.getTimecardUser()));
					employeeDto.setStartDate(employee.getStartDate());
					employeeDto.setIsManager(employee.getIsManager());
					
					employeeDtos.add(employeeDto);
					
				}
			}
			}
		}
			
			return employeeDtos;
		}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param organizationId
	 * @return totalResults..total number of employees and managers
	 * @throws Exception
	 * 
	 * Method For get total results All employees i.e.. both employees and managers
	 */
	
	public Integer getTotalResultsOfEmployeesAndManagers(Integer organizationId) throws Exception{
		log.info("inside getTotalResultsOfEmployeesAndManagers()");
		Integer totalResults=this.employeeDao.getTotalOfEmployeesAndManagers(organizationId);
		return totalResults;
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param organizationId
	 * @return totalResults..total number of employees
	 * @throws Exception
	 * 
	 * Method For Get the total results of Employees
	 */
	
	public Integer getTotalResultsOfEmployees(Integer organizationId) throws Exception{
		log.info("inside getTotalResultsOfEmployees()");
		Integer totalResults=this.employeeDao.getTotalEmployees(organizationId);
		return totalResults;
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param organizationId
	 * @return totalResults..total number of managers
	 * @throws Exception
	 * 
	 * Method For get the total results of Managers
	 */
	public Integer getTotalResultsOfManagers(Integer organizationId) throws Exception{
		log.info("inside getTotalResultsOfManagers()");
		Integer totalResults=this.employeeDao.getTotalManagers(organizationId);
		return totalResults;
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param timecarduserId
	 * @return employeeDto
	 * @throws EmployeeNotFoundException
	 * 
	 * Method For getting the employee based on timecard userid
	 */
	
	public EmployeeDto getTheEmployeeByTimecardUserId(Integer userId) throws EmployeeNotFoundException{
		log.info("inside getTheEmployeeByTimecardUserId()");
		Employee employee=this.employeeDao.getTheEmployeeByTimecardUserId(userId);
		EmployeeDto employeeDto=EmployeeDto.populateEmployeeDto(employee);
		return employeeDto;
	}
	
	/**
	 * Craeted By Bhagya On October 28th,2014
	 * @param employeeDto
	 * @return savedResult
	 * @throws Exception
	 * 
	 * Method For Saving the employee As A Manager,
	 * we used this method at the time whenever we added employee as a manager
	 */
	
	
	public Integer saveEmployeeAsManager(EmployeeDto employeeDto) throws Exception{
		Employee employee=this.employeeDao.getTheEmployeeByEmployeeNo(employeeDto.getEmployeeNo());
		employee.setIsManager(true);
		TimecardUser user=employee.getTimecardUser();
		Organization organization=employee.getOrganization();
		organization.getSiteManagers().add(user);
		this.organizationDao.saveorUpdateOrganization(organization);
		employee.setOrganization(organization);
		Integer savedResult=this.employeeDao.saveorUpdateEmployeeDetails(employee);
		return savedResult;
		
	}
	/**
	 * Created By Bhagya On October 28th,2014
	 * @param employeeDto,divisionId,organizationId
	 * @return savedResult..confirmation result for saving the manager details
	 * @throws Exception
	 * 
	 * Method For process Of Saving the manager details
	 */
	
	public Integer savingAddedManagerDetails(EmployeeDto employeeDto,Integer divisionId,Integer organizationId) throws Exception{
		log.info("inside savingAddedManagerDetails");
		Employee employee=new Employee();
		employee.setEmployeeNo(employeeDto.getEmployeeNo());
		employee.setStartDate(employeeDto.getStartDate());
		TimecardUser user=new TimecardUser();
		user.setDateofBirth(employeeDto.getTimecardUser().getDateofBirth());
		user.setFirstName(employeeDto.getTimecardUser().getFirstName());
		user.setLastName(employeeDto.getTimecardUser().getLastName());
		TimeCardUserCredentials timecardcredentials=new TimeCardUserCredentials();
		timecardcredentials.setEmail(employeeDto.getTimecardUser().getTimeCardCredentials().getEmail());
		String encryPassword=passwordEncoder.encode(employeeDto.getTimecardUser().getTimeCardCredentials().getPassword());
		timecardcredentials.setPassword(encryPassword);
		timecardcredentials.setUsername(employeeDto.getTimecardUser().getTimeCardCredentials().getUsername());
		user.setTimeCardCredentials(timecardcredentials);
		this.userDao.saveOrUpdateTimecardCredentials(timecardcredentials);
		this.userDao.saveOrUpdateTimecardUser(user);
		employee.setTimecardUser(user);
		employee.setIsManager(true);
		Division division=this.divisionDao.getDivisionByDivisionId(divisionId);
		employee.setDivision(division);
		Organization organization=this.organizationDao.getOrganizationById(organizationId);
		organization.getSiteManagers().add(user);
		this.organizationDao.saveorUpdateOrganization(organization);
		employee.setOrganization(organization);
		Integer savedResult=this.employeeDao.saveorUpdateEmployeeDetails(employee);
		return savedResult;
	}
	/**
	 * Craeted By Bhagya On October 28th,2014
	 * @param employeeDto
	 * @return savedResult... confirmation result for removing the organization manager
	 * @throws Exception
	 * 
	 * Method For removing the Organization Manager
	 */
	
	public Integer removeOrganizationManager(EmployeeDto employeeDto) throws Exception{
		log.info("inside removeOrganizationManager()");
		Employee employee=this.employeeDao.getTheEmployeeByEmployeeNo(employeeDto.getEmployeeNo());
		employee.setIsManager(false);
		TimecardUser user=employee.getTimecardUser();
		Organization organization=employee.getOrganization();
		organization.getSiteManagers().remove(user);
		this.organizationDao.saveorUpdateOrganization(organization);
		Integer savedResult=this.employeeDao.saveorUpdateEmployeeDetails(employee);
		return savedResult;
	}
	/**
	 * Craeted By Bhagya On October 28th,2014
	 * @param employeeDto,divisionId,organizationId
	 * @return savedResult...Confirmation Results for saving the Edited details of employee
	 * @throws Exception
	 * 
	 * 
	 * Method For Saving The Updated or edited Details for employee
	 */
	
	public Integer savingUpdatedEmployeeDetails(EmployeeDto employeeDto,Integer divisionId,Integer organizationId) throws Exception{
		log.info("inside savingUpdatedEmployeeDetails()");
		Employee employee=this.employeeDao.getTheEmployeeByEmployeeNo(employeeDto.getEmployeeNo());
		employee.setEmployeeNo(employeeDto.getEmployeeNo());
		employee.setStartDate(employeeDto.getStartDate());
		TimecardUser user=this.userDao.getTimecardUserByEmail(employeeDto.getTimecardUser().getTimeCardCredentials().getEmail());
		user.setDateofBirth(employeeDto.getTimecardUser().getDateofBirth());
		user.setFirstName(employeeDto.getTimecardUser().getFirstName());
		user.setLastName(employeeDto.getTimecardUser().getLastName());
		TimeCardUserCredentials timecardcredentials=new TimeCardUserCredentials();
		timecardcredentials.setEmail(employeeDto.getTimecardUser().getTimeCardCredentials().getEmail());
		timecardcredentials.setUsername(employeeDto.getTimecardUser().getTimeCardCredentials().getUsername());
		user.setTimeCardCredentials(timecardcredentials);
		this.userDao.saveOrUpdateTimecardCredentials(timecardcredentials);
		this.userDao.saveOrUpdateTimecardUser(user);
		employee.setTimecardUser(user);
		employee.setIsManager(employeeDto.getIsManager());
		Division division=this.divisionDao.getDivisionByDivisionId(divisionId);
		employee.setDivision(division);
		Organization organization=this.organizationDao.getOrganizationById(organizationId);
		employee.setOrganization(organization);
		Integer savedResult=this.employeeDao.saveorUpdateEmployeeDetails(employee);
		return savedResult;
	}
}