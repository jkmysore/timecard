package org.kns.timecard.frontend.admin.service;

import java.util.ArrayList;

import org.kns.timecard.exception.OrganizationNotFoundException;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;



public interface SuperAdminService {
	public Integer createOrganization(OrganizationDto organizationDto)throws Exception;
	public ArrayList<OrganizationDto> getOrganizations(Integer pageNo,Integer pageSize,String searchBy,String sortBy,Boolean ascending) throws OrganizationNotFoundException;
	public OrganizationDto getOrganizationByOrganizationId(Integer organizationId)throws Exception;
	public Integer editOrganizationByAdmin(OrganizationDto organizationDto)throws Exception;
	public Integer changeOrganizationStatus(Integer organizationId,Boolean status)throws Exception;
	public Integer saveOrganizationSettings(OrganizationDto organizationDto) throws OrganizationNotFoundException,Exception;
	
}
