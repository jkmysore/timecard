package org.kns.timecard.frontend.common.utility;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.service.OrganizationService;
import org.springframework.stereotype.Component;


/**
 * Created by Jeevan on  January 23, 2014
 * Class to determine LoggerUtiltiy of Organizations.
 * 
 * All Classes needs to access methods tof LoggerUtility class to determine whether to log organization details
 * amd Organization Employee level details
 * 
 * @author KNS-ACCONTS
 *
 */
@Component("loggerUtility")
public class LoggerUtility {

	private static Logger log=Logger.getLogger(LoggerUtility.class);
	
	@Resource(name="organizationService")
	public  OrganizationService organizationService;
	
	
	/**
	 * 
	 * @param organizationId
	 * @return
	 * @throws OrganizationNotFoundException
	 */
	public  LogDto saveOrganizationLogs(Integer organizationId)throws OrganizationNotFoundException{
		log.info("inside saveOrganizationLogs()");
		
		System.out.println(organizationId);
		OrganizationDto organizationDto=this.organizationService.getOrganizationById(organizationId);
		LogDto logDto=new LogDto();
		logDto.setSaveOrganizationLog(organizationDto.getIsLogsSaved());
		logDto.setSaveOrganizationUserLog(organizationDto.getIsUserLogsSaved());
		return logDto;
		
	}
	
	
}
