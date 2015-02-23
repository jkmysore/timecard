package org.kns.timecard.frontend.organization.organization.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.admin.dao.OrganizationDao;
import org.kns.timecard.backend.organization.employee.dao.EmployeeDao;
import org.kns.timecard.backend.organization.employee.model.Employee;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.organization.organization.model.TimeCardPeriod;
import org.kns.timecard.backend.timecarduser.dao.UserDao;
import org.kns.timecard.backend.timecarduser.model.Roles;
import org.kns.timecard.backend.timecarduser.model.TimeCardUserCredentials;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.common.logging.LoggingFactory;
import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.common.utility.EmailSender;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationConfigDto;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.dto.TimeCardPeriodDto;
import org.kns.timecard.frontend.timecarduser.dto.TimeCardUserCredentialsDto;
import org.kns.timecard.frontend.timecarduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 *
 *Created on October 15th, 2014
 * Service for Organization
 */
@Transactional
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	private static Logger log=Logger.getLogger(OrganizationServiceImpl.class);
	
	
	@Resource(name="organizationDao")
	private OrganizationDao organizationDao;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="employeeDao")
	private EmployeeDao employeeDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Resource(name="emailSender")
	private EmailSender emailSender;
	
	
	private String logoPath;
	
	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	private File destinationFile;


	
	/**
	 * Created by Jeevan on Jan 23, 2015
	 * Method to get Organization by Organization Id
	 */
	public OrganizationDto getOrganizationById(Integer organizationId)throws OrganizationNotFoundException{
		log.info("inside getOrganizationById()");
		Organization organization=this.organizationDao.getOrganizationById(organizationId);
		System.out.println("ORG "+organization.getOrganizationId());
		OrganizationDto organizationDto=OrganizationDto.populateOrganizationDto(organization);
		System.out.println("ORG 2"+organizationDto.getOrganizationId());
		return organizationDto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Created by Bhagya on October 15, 2014
	 * @param organizationDto
	 * @return savedresult of organization
	 * 
	 * 
	 * Method to save Update Organization
	 * Step;
	 * 
	 * 1. Save User
	 * 2. populate Organization with Assigned User with Roles SITE_ADMIN
	 * 3. Save Logo
	 * 4. after Organization creation
	 * 5. Send Confirmation Mail to User
	 * 
	 * 
	 */
	public Integer createOrganization(OrganizationDto organizationDto)throws Exception{
		log.info("inside createOrganizationFromSuperAdmin()");
		TimecardUser timecardUser=this.saveTimecardUser(organizationDto.getSiteAdmin().getTimeCardCredentials());
		Organization organization=new Organization();
		organization.setOrganizationName(organizationDto.getOrganizationName());
		organization.setOrganizationShortName(organizationDto.getOrganizationShortName());
		organization.setCreatedBy(this.userDao.getTimecardUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		organization.setCreatedDate(new Date());
		organization.setSiteAdmin(timecardUser);
		organization.setLogoPath(organizationDto.getLogo().getOriginalFilename());
		this.saveLogoFile(organizationDto.getLogo());
		Integer savedResult=this.organizationDao.saveorUpdateOrganization(organization);
		this.emailSender.sendOrganizationCreationMail(organizationDto);
		final Logger logs=LoggingFactory.getCustomLogger(organization.getOrganizationName(), null, true);
		logs.info("Organization "+organization.getOrganizationName()+" created Successfully on "+new Date());
		
		return savedResult;
	}
	
	
	
	
	
	/**
	 * Created by Bhagya on October 15, 2014
	 * @param username,password,email
	 * @return timecarduser
	 * 
	 * Method to Create Timecard user for Organization
	 * 
	 */
	private TimecardUser saveTimecardUser(TimeCardUserCredentialsDto timecardCredentials)throws Exception{
		log.info("inside saveTimecardUser()");
		TimecardUser timecardUser=new TimecardUser();
		TimeCardUserCredentials timecardUserCredentials=new TimeCardUserCredentials();
		timecardUserCredentials.setUsername(timecardCredentials.getUsername());
		timecardUserCredentials.setPassword(passwordEncoder.encode(timecardCredentials.getPassword()));
		timecardUserCredentials.setEmail(timecardCredentials.getEmail());
		Roles role=this.userDao.getRoleByRoleName("ROLE_SITE_ADMIN");
		Set<Roles> roles=new HashSet<Roles>();
		roles.add(role);
		timecardUserCredentials.setRole(roles);
		this.userDao.saveOrUpdateTimecardCredentials(timecardUserCredentials);
		timecardUser.setCreatedBy(this.userDao.getTimecardUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		timecardUser.setCreatedDate(new Date());
		timecardUser.setIsActive(true);
		timecardUser.setIsEnabled(true);
		timecardUser.setIsLocked(true);
		timecardUser.setIsFirstLogin(true);
		timecardUser.setTimeCardCredentials(timecardUserCredentials);
		this.userDao.saveOrUpdateTimecardUser(timecardUser);
		Employee employee=new Employee();
		employee.setTimecardUser(timecardUser);
		this.employeeDao.saveorUpdateEmployeeDetails(employee);
		return timecardUser;		
	}
	
	
	
	
	/**
	 * Created By Bhagya on 15-oct-2014
	 * @param Logo
	 * @return IOException
	 * 
	 * Utility method to save Logo files
	 */	
	private void saveLogoFile(MultipartFile logo) throws IOException{
		log.info("inside saveLogoFile()");
		System.out.println(logo.getOriginalFilename()+"fileNAme ");
		File imagesFolder=new File(logoPath+"/"+"organization");
		if(!imagesFolder.exists()){
			imagesFolder.mkdir();
			 destinationFile=new File(imagesFolder.getAbsolutePath()+"/"+logo.getOriginalFilename());
			
		 }
		 destinationFile=new File(imagesFolder.getAbsolutePath()+"/"+logo.getOriginalFilename());		 
		 logo.transferTo(destinationFile);	
		}
	
	
	/**
	 * Created By Bhagya on 21-oct-2014
	 * @param 
	 * @return timecardPeriodDtos
	 * 
	 * Method to get all Timecardperiodsfrom database
	 */
	public ArrayList<TimeCardPeriodDto> getTimecardPeriodsFromDB()throws Exception{
		log.info("inside getTimeCardPeriodsFromDB()");
		ArrayList<TimeCardPeriod> timecardPeriods=this.organizationDao.getTimecardPeriods();
		ArrayList<TimeCardPeriodDto> timecardPeriodDtos=new ArrayList<TimeCardPeriodDto>();
		for(TimeCardPeriod period:timecardPeriods){
			TimeCardPeriodDto periodDto=TimeCardPeriodDto.populateTimecardPeriodDto(period);
		    timecardPeriodDtos.add(periodDto);
		}
		return timecardPeriodDtos;
	}
	
	/**
	 * Created By Bhagya on 21-oct-2014
	 * @param 
	 * @return
	 * 
	 * Method to save Organization Configuration
	 * 
	 * 
	 * Steps:
	 * 1. Get User by User Id
	 * 2  Get Organization of User
	 * 3. set Config Parameters Update
	 */
	public Integer processSavingConfigurationOfOrganization(OrganizationConfigDto configDto,Integer userId)throws Exception{
		 log.info("inside processSavingConfigurationOfOrganization()");
		
		 
		/* TimecardUser timecardUser=this.userDao.getUserByUserId(userId);
		Organization organization=this.organizationDao.getOrganizationByAdmin(timecardUser);
		 organization.setMaxHoursPerWeek(configDto.getMaxHoursPerWeek());
		 organization.setMinHoursPerWeek(configDto.getMinHoursPerWeek());
		 organization.setWeekEndingDay(configDto.getWeekEndingDay());
		 organization.setWhineList(configDto.getWhineList());
		 TimeCardPeriod period=this.organizationDao.getTimeCardPeriodById(configDto.getTimecardPeriod().getId());
		 organization.setTimeCardPeriod(period);
		 organization.setModifiedBy(timecardUser);
		 organization.setModifiedDate(new Date());
		 Integer result =this.organizationDao.saveorUpdateOrganization(organization);
		 return result;*/
		 return null;
	 }
	/**
	 * Created By Bhagya on October 21st ,2014
	 * @param useremail
	 * @return organizationDtos
	 * @throws TimecardUserNotFoundException,OrganizationNotFoundException
	 * 
	 * Method For Get the organizations Based on Manager
	 * 
	 */
	
	public ArrayList<OrganizationDto> getOrganizationsByAdminorManager(String useremail) throws TimecardUserNotFoundException, OrganizationNotFoundException{
		TimecardUser timeCardUser=this.userDao.getTimecardUserByEmail(useremail);
		ArrayList<Organization> organizationDetails=this.organizationDao.getOrganizationsByAdminorManager(timeCardUser);
		ArrayList<OrganizationDto> organizationDtos=new ArrayList<OrganizationDto>();
		for(Organization organization:organizationDetails){
			OrganizationDto dtos=OrganizationDto.populateOrganizationDto(organization);
			organizationDtos.add(dtos);
		}
		return organizationDtos;		
	}
	
	
	
	
	
	
	
	
	
}
	