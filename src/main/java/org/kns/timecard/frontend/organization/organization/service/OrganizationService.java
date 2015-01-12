package org.kns.timecard.frontend.organization.organization.service;

import java.util.ArrayList;

import org.kns.timecard.backend.organization.organization.exception.OrganizationNotFoundException;
import org.kns.timecard.backend.timecarduser.exception.TimecardUserNotFoundException;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.organization.organization.dto.TimeCardPeriodDto;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationConfigDto;

/**
 * 
 * @author Bhagya -- KNS Technologies Corporation
 *
 *	Created by Bhagya on October 15th, 2014
 *Interface for service layer, Organization
 *
 *
 */
public interface OrganizationService {

	public Integer createOrganization(OrganizationDto organizationDto)throws Exception;
	public ArrayList<TimeCardPeriodDto> getTimecardPeriodsFromDB()throws Exception;
	public Integer processSavingConfigurationOfOrganization(OrganizationConfigDto configDto,Integer userId)throws Exception;
	public ArrayList<OrganizationDto> getOrganizationsByAdminorManager(String useremail) throws TimecardUserNotFoundException, OrganizationNotFoundException;
}
