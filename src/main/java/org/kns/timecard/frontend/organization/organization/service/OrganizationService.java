package org.kns.timecard.frontend.organization.organization.service;

import java.util.ArrayList;

import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.exception.TimecardUserNotFoundException;
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

	public OrganizationDto getOrganizationById(Integer organizationId)throws OrganizationNotFoundException;
	
	public Integer createOrganization(OrganizationDto organizationDto)throws Exception;
	public ArrayList<TimeCardPeriodDto> getTimecardPeriodsFromDB()throws Exception;
	public Integer processSavingConfigurationOfOrganization(Integer userId,String organizationName,String timecardPeriod,String weekEndingDay,String minHoursPerWeek,
			String maxHoursPerWeek,String whineList)throws Exception;
	public ArrayList<OrganizationDto> getOrganizationsByAdminorManager(String useremail) throws TimecardUserNotFoundException, OrganizationNotFoundException;
	public Integer saveActivationDetailsOfOrganization(ArrayList<OrganizationDto> organizationDto) throws OrganizationNotFoundException,Exception;
	public OrganizationDto getOrganizationByUserId(Integer userId)throws OrganizationNotFoundException;
	public OrganizationDto getOrganizationByUserEmail(String userEmail)throws TimecardUserNotFoundException,OrganizationNotFoundException;
	

}
