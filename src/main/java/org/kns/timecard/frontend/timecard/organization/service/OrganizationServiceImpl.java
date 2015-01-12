package org.kns.timecard.frontend.timecard.organization.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.timecard.organization.dao.OrganizationDao;
import org.kns.timecard.backend.timecard.organization.model.Organization;
import org.kns.timecard.backend.timecard.organization.model.TimeCardPeriod;
import org.kns.timecard.backend.timecard.timecard.model.TimeCard;
import org.kns.timecard.backend.user.dao.UserDao;
import org.kns.timecard.backend.user.model.Roles;
import org.kns.timecard.backend.user.model.TimeCardUser;
import org.kns.timecard.common.logging.LoggingFactory;
import org.kns.timecard.frontend.common.utility.EmailSender;
import org.kns.timecard.frontend.timecard.organization.dto.OrganizationConfigDto;
import org.kns.timecard.frontend.timecard.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.timecard.organization.dto.TimecardPeriodDto;
import org.kns.timecard.frontend.user.dto.RolesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 *Created on June 27, 2014
 * Service for Organization
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	private static Logger log=Logger.getLogger(OrganizationServiceImpl.class);
	
	
	@Resource(name="organizationDao")
	private OrganizationDao organizationDao;
	
	
	@Resource(name="userDao")
	private UserDao userDao;
	
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




	/*
	 * Created by Jeevan on June 27, 2014
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
	public Integer createOrganizationfromSuperAdmin(OrganizationDto organizationDto)throws Exception{
		log.info("inside createOrganizationFromSuperAdmin()");
		TimeCardUser timecardUser=this.saveTimecardUser(organizationDto.getSiteAdminUserName(), organizationDto.getSiteAdminPassword(), organizationDto.getSiteAdminEmail());
		Organization organization=new Organization();
		organization.setOrganizationName(organizationDto.getOrganizationName());
		organization.setOrganizationShortName(organizationDto.getOrganizationShortName());
		organization.setCreatedBy(this.userDao.getUserByUsernameorEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		organization.setCreatedDate(new Date());
		organization.setSiteAdmin(timecardUser);
		organization.setLogoPath(organizationDto.getLogo().getOriginalFilename());
		this.saveLogoFile(organizationDto.getLogo());
		Integer savedResult=this.organizationDao.saveorUpdateOrganization(organization);
		OrganizationDto organizationsDto=OrganizationDto.populateOrganizationDto(organization);
		organizationsDto.getSiteAdmin().setPassword(organizationDto.getSiteAdminPassword());
		this.emailSender.sendOrganizationCreationMail(organizationsDto);
		final Logger logs=LoggingFactory.getCustomLogger(organization.getOrganizationName(), null, true);
		logs.info("Organization "+organization.getOrganizationName()+" created Successfully on "+new Date());
		
		return savedResult;
	}
	
	
	/*
	 * Created by User on June 30, 2014
	 * Method to Create Timecard user for Organization
	 * 
	 */
	private TimeCardUser saveTimecardUser(String username,String password,String email)throws Exception{
		log.info("inside saveTimecardUser()");
		TimeCardUser timecardUser=new TimeCardUser();
		timecardUser.setUsername(username);
		timecardUser.setPassword(passwordEncoder.encode(password));
		timecardUser.setEmail(email);
		Roles role=this.userDao.getRoleByRoleName("ROLE_SITE_ADMIN");
		Set<Roles> roles=new HashSet<Roles>();
		roles.add(role);
		timecardUser.setRole(roles);
		timecardUser.setCreatedBy(this.userDao.getUserByUsernameorEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		timecardUser.setCreatedDate(new Date());
		timecardUser.setIsActive(true);
		timecardUser.setIsEnabled(true);
		timecardUser.setIsFirstLogin(true);
		this.userDao.saveOrUpdateTimecardUser(timecardUser);
		return timecardUser;		
	}
	
	
	
	/*
	 * Created by Jeevan on July 03, 2014
	 * Method to get all Timecardperiodsfrom database
	 */
	public ArrayList<TimecardPeriodDto> getTimecardPeriodsFromDB()throws Exception{
		log.info("inside getTimeCardPeriodsFromDB()");
		ArrayList<TimeCardPeriod> timecardPeriods=this.organizationDao.getTimecardPeriods();
		ArrayList<TimecardPeriodDto> timecardPeriodDtos=new ArrayList<TimecardPeriodDto>();
		for(TimeCardPeriod period:timecardPeriods){
			TimecardPeriodDto periodDto=TimecardPeriodDto.populateTimecardPeriodDto(period);
		    timecardPeriodDtos.add(periodDto);
		}
		return timecardPeriodDtos;
	}
	
	
	/*
	 * Create by Jeevan on July 03, 2014
	 * method to get TTimecardPeriodbyId
	 */
	public TimecardPeriodDto getTimecardPeriodById(Integer id)throws Exception{
		log.info("inside getTimecardPeriodById()");
		TimeCardPeriod timeCardPeriod=this.organizationDao.getTimeCardPeriodById(id);
		TimecardPeriodDto timecardPeriodDto=TimecardPeriodDto.populateTimecardPeriodDto(timeCardPeriod);
		return timecardPeriodDto;
	}
	
	
	
	
	/*
	 * Added by Jeevan on July 03, 2014
	 * 
	 * Method to save Organizzation Configuration
	 * 
	 * 
	 * Steps:
	 * 1. Get User by User Id
	 * 2  Get Organization of User
	 * 3. set Config Parameters Update
	 * 
	 */
	 public Integer processSavingConfigurationOfOrganization(OrganizationConfigDto configDto,Integer userId)throws Exception{
		 log.info("inside processSavingConfigurationOfOrganization()");
		 TimeCardUser timecardUser=this.userDao.getUserByUserId(userId);
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
		 return result;
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Utiltity method to save Logo files
	 */
	private void saveLogoFile(MultipartFile logo) throws IOException{
		log.info("inside saveLogoFile()");
		System.out.println(logo.getOriginalFilename()+"fileNAme ");
		File file=new File(logoPath+logo.getOriginalFilename());
		if(file.exists()){
			file.createNewFile();
		}
		logo.transferTo(file);
	}
	
	
	
	
	
	
	
	
}
