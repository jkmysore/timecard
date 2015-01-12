package org.kns.timecard.frontend.organization.division.service;



import java.util.ArrayList;

import javax.annotation.Resource;

import org.kns.timecard.backend.organization.division.dao.DivisionDao;
import org.kns.timecard.backend.organization.division.exception.DivisionNotFoundException;
import org.kns.timecard.backend.organization.division.model.Division;
import org.kns.timecard.backend.organization.organization.dao.OrganizationDao;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.frontend.organization.division.dto.DivisionDto;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

/**
 * 
 * @author Bhagya -KNS Technologies Corporation
 * 
 * Created On October 22nd,2014
 * Service for Division
 *
 */


@Service("divisionService")
public class DivisionServiceImpl implements DivisionService {

	private static Logger log=Logger.getLogger(DivisionServiceImpl.class);
	
	
	@Resource(name="organizationDao")
	private OrganizationDao organizationDao;
	
	@Resource(name="divisionDao")
	private DivisionDao divisionDao;
	
	/**
	 * Created By Bhagya On October 22nd,2014
	 * @param divisionDto,organizationId
	 * @return Integer Value of Saved Result
	 * @throws Exception
	 * 
	 *  Method for Saving The department Details into Db
	 */
	public Integer savingTheAddedorUpdatedDivisionDetails(DivisionDto divisionDto,Integer organizationId) throws Exception{
		log.info("inside savingTheAddedDepartmentDetails()");
		Division division=new Division();
		division.setDivisionId(divisionDto.getDivisionId());
		division.setDivisionNo(divisionDto.getDivisionNo());
		division.setDivisionName(divisionDto.getDivisionName());
		Organization organization=this.organizationDao.getOrganizationById(organizationId);
		division.setOrganization(organization);
		Integer savedResult=this.divisionDao.saveorUpdateDivisionDetails(division);
		return savedResult;
	}
	
	/**
	 * Craeted By Bhagya on October 22nd,2014
	 * @param page,pagesize,organizationId
	 * @return divisionDtos
	 * @throws DivisionNotFoundException
	 * 
	 * Method For Getting All Divisions Based On Organization Id
	 */
	public ArrayList<DivisionDto> getAllDivisionsBasedOnOrganizationId(Integer page,Integer pageSize,Integer organizationId) throws DivisionNotFoundException{
		ArrayList<Division> divisions=this.divisionDao.getAllDivisionsBasedOnOrganizationIdFromDB(page, pageSize,organizationId);
		ArrayList<DivisionDto> divisionsDtos=new ArrayList<DivisionDto>();
		for(Division division:divisions){
			DivisionDto divisionDto=new DivisionDto();
			divisionDto.setDivisionId(division.getDivisionId());
			divisionDto.setDivisionName(division.getDivisionName());
			divisionDto.setDivisionNo(division.getDivisionNo());
			divisionDto.setOrganization(OrganizationDto.populateOrganizationDto(division.getOrganization()));
			divisionsDtos.add(divisionDto);
			
		}
				
				return divisionsDtos;
		
	}
	
	/**
	 * Created By Bhagya on october 22nd,2014
	 * @param
	 * @return Count Value Of Total Results Of Division
	 * @throws Exception
	 * 
	 * Method For getting the Total Results Of Division
	 */
	public Integer getDivisionTotalResults() throws Exception{
		log.info("inside getDivisionTotalResults()");
		Integer totalResults=this.divisionDao.getTotalDivisions();
		return totalResults;
		
	}
	/**
	 * Created By bhagya On October 22nd,2014
	 * @param  divisionId
	 * @return  divisionDto
	 * @throws DivisionNotFoundException
	 * 
	 * Method For Getting The DiVision Details Based On Division Id
	 */
	public DivisionDto getDivisionDetailsByDivisionId(Integer divisionId) throws DivisionNotFoundException{
		log.info("inside getDivisionDetailsByDivisionId()");
		Division division=this.divisionDao.getDivisionByDivisionId(divisionId);
		DivisionDto divisionDto=new DivisionDto();
		divisionDto.setDivisionId(division.getDivisionId());
		divisionDto.setDivisionName(division.getDivisionName());
		divisionDto.setDivisionNo(division.getDivisionNo());
		divisionDto.setOrganization(OrganizationDto.populateOrganizationDto(division.getOrganization()));
		return divisionDto;
		
	}
}