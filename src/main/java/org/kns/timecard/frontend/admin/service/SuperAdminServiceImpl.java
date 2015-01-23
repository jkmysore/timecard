package org.kns.timecard.frontend.admin.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.backend.admin.dao.AdminDao;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.timecarduser.dao.UserDao;
import org.kns.timecard.backend.timecarduser.model.Roles;
import org.kns.timecard.backend.timecarduser.model.TimeCardUserCredentials;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.common.logging.LoggingFactory;
import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.frontend.common.utility.EmailSender;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.timecarduser.dto.TimeCardUserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;





/**
 * 
 * Created by Jeevan on Jan 12, 2015 * 
 * Service layer class for SuperAdminFunctionalities
 * 
 */
@Transactional
@Service("superAdminService")
public class SuperAdminServiceImpl implements SuperAdminService {

	
	private static Logger log=Logger.getLogger(SuperAdminServiceImpl.class);
	
	
	@Resource(name="adminDao")
	private AdminDao adminDao;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Resource(name="emailSender")
	private EmailSender emailSender;
	
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	
	private String logoPath;

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	
	
	
	
	
	
	
	
	
	/**
	 * Created by Jeevan on Jan 12, 2015
	 * Method to create Organization..
	 * @param organizationDto
	 * @return
	 * @throws Exception
	 * 
	 * Steps:	 
	 * 1. Create TimecardUser with given credentials...
	 * 2. Create Organization...
	 * 3. Create Employee..
	 * 4. Send Organization Creation Mail..
	 * 
	 * 
	 */
	@Transactional
	public Integer createOrganization(OrganizationDto organizationDto)throws Exception{
		log.info("inside createOrganization()");
		TimecardUser timecardUser=this.saveTimecardUser(organizationDto.getSiteAdmin().getTimeCardCredentials());
		Organization organization=new Organization();
		organization.setOrganizationName(organizationDto.getOrganizationName());
		organization.setOrganizationShortName(organizationDto.getOrganizationShortName());
		organization.setCreatedBy(this.userDao.getTimecardUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		organization.setCreatedDate(new Date());
		organization.setSiteAdmin(timecardUser);
		organization.setLogoPath(organizationDto.getLogo().getOriginalFilename());
		organization.setIsActive(true);
		organization.setIsLogsSaved(true);
		organization.setIsUserLogsSaved(true);
		Integer savedResult=this.adminDao.saveorUpdateOrganization(organization);
		this.saveLogoFile(organizationDto.getLogo(),savedResult);
		//Needs to include logic to save Employee..
		
		
		this.emailSender.sendOrganizationCreationMail(organizationDto);
		final Logger logs=LoggingFactory.getCustomLogger(organization.getOrganizationName(), null, true);
		logs.info("Organization "+organization.getOrganizationName()+" created Successfully on "+new Date());		
		return savedResult;		
	}
	
	
	/**
	 * Created by jeevan on Jan 21, 2015
	 * Method to get Organizations on Service layer..
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param searchBy
	 * @param sortBy
	 * @param ascending
	 * @return
	 * @throws OrganizationNotFoundException 
	 */
	public ArrayList<OrganizationDto> getOrganizations(Integer pageNo,Integer pageSize,String searchBy,String sortBy,Boolean ascending) throws OrganizationNotFoundException{
		log.info("inside getOrganizations()");
		ArrayList<OrganizationDto> organizationDtos=new ArrayList<OrganizationDto>();
		ArrayList<Organization> organizations=this.adminDao.getAllOrganizations(pageNo, pageSize, searchBy, sortBy, ascending);
		for(Organization organization:organizations){
			
			/*OrganizationDto organizationDto=new OrganizationDto();
			organizationDto.setOrganizationId(organization.getOrganizationId());
			organizationDto.setOrganizationName(organization.getOrganizationName());
			organizationDto.setOrganizationShortName(organization.getOrganizationShortName());
			organizationDto.setLogoPath(organization.getLogoPath());
			organizationDto.setIsActive(organization.getIsActive());*/
			OrganizationDto organizationDto=OrganizationDto.populateOrganizationDto(organization);
			if(null!=organization.getTotalOrganizations()){
				organizationDto.setTotalOrganizations(organization.getTotalOrganizations());
			}
			organizationDtos.add(organizationDto);
		}
		
		return organizationDtos;
	}
	
	
	
	
	/**
	 * 
	 * @param timecardCredentials
	 * @return
	 * @throws Exception
	 */
	private TimecardUser saveTimecardUser(TimeCardUserCredentialsDto timecardCredentialDto)throws Exception{
		log.info("inside saveTimecardUser()");
		TimecardUser timecardUser=new TimecardUser();
		TimeCardUserCredentials timecardUserCredentials=new TimeCardUserCredentials();
		timecardUserCredentials.setUsername(timecardCredentialDto.getUsername());
		timecardUserCredentials.setPassword(passwordEncoder.encode(timecardCredentialDto.getPassword()));
		timecardUserCredentials.setEmail(timecardCredentialDto.getEmail());
		Roles role=this.userDao.getRoleByRoleName("ROLE_SITE_ADMIN");
		Set<Roles> roles=new HashSet<Roles>();
		roles.add(role);
		timecardUserCredentials.setRole(roles);
		this.userDao.saveOrUpdateTimecardCredentials(timecardUserCredentials);
		timecardUser.setCreatedBy(this.userDao.getTimecardUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
		timecardUser.setCreatedDate(new Date());
		timecardUser.setIsActive(true);
		timecardUser.setIsEnabled(true);
		timecardUser.setIsLocked(false);
		timecardUser.setIsFirstLogin(true);
		timecardUser.setTimeCardCredentials(timecardUserCredentials);
		this.userDao.saveOrUpdateTimecardUser(timecardUser);
		return timecardUser;		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Created By Bhagya on 15-oct-2014
	 * @param Logo
	 * @return IOException
	 * 
	 * Utility method to save Logo files
	 */
	
	private void saveLogoFile(MultipartFile logo,Integer folder) throws IOException{
		log.info("inside saveLogoFile()");
		System.out.println(logo.getOriginalFilename()+"fileNAme ");
		File imagesFolder=new File(logoPath+"/"+"org/"+folder);
		File destinationFile=null;
		if(!imagesFolder.exists()){
			imagesFolder.mkdir();
			 destinationFile=new File(imagesFolder.getAbsolutePath()+"/"+logo.getOriginalFilename());			
		 }
		 destinationFile=new File(imagesFolder.getAbsolutePath()+"/"+logo.getOriginalFilename());
		 
		 logo.transferTo(destinationFile);
	
		}
	
	
	
	
	
}
