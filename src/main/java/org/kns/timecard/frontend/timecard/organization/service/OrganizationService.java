package org.kns.timecard.frontend.timecard.organization.service;

import java.util.ArrayList;

import org.kns.timecard.backend.timecard.organization.model.TimeCardPeriod;
import org.kns.timecard.frontend.timecard.organization.dto.OrganizationConfigDto;
import org.kns.timecard.frontend.timecard.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.timecard.organization.dto.TimecardPeriodDto;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 *	Created by Jeevan on June 27 , 2014
 *Interdface for service layer, Organization
 *
 *
 */
public interface OrganizationService {

	public Integer createOrganizationfromSuperAdmin(OrganizationDto organizationDto)throws Exception;
	public ArrayList<TimecardPeriodDto> getTimecardPeriodsFromDB()throws Exception;
	public TimecardPeriodDto getTimecardPeriodById(Integer id)throws Exception;
	 public Integer processSavingConfigurationOfOrganization(OrganizationConfigDto configDto,Integer userId)throws Exception;
}
