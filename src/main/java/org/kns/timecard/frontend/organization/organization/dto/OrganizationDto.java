package org.kns.timecard.frontend.organization.organization.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.kns.timecard.backend.organization.organization.model.Organization;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.springframework.web.multipart.MultipartFile;
/**
 * Created by Bhagya on September 10, 2014
 * Dto for Organization
 *
 */

public class OrganizationDto{
	private Integer organizationId;
	@NotNull
	@NotBlank
	private String organizationName;
	@NotNull
	@NotBlank
	private String organizationShortName;
	private String logoPath;
	private MultipartFile logo;
	private Date createdDate;
	private TimecardUserDto createdBy;
	private Date modifiedDate;
	private TimecardUserDto modifiedBy;
	private String 	theme;
	private TimeCardPeriodDto timeCardPeriod;
	private TimecardUserDto siteAdmin;
	private Set<TimecardUserDto> siteManagers=new HashSet<TimecardUserDto>();
	private Boolean isActive;
	
	//Created on Jan 21, 2015 by Jeevan
	private Boolean isLogsSaved;
	private Boolean isUserLogsSaved;
	
	//Added on Feb 09, 2015
	private String status;
	private Date statusDate;
	
	private Integer totalOrganizations;
	
	
	
	
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationShortName() {
		return organizationShortName;
	}
	public void setOrganizationShortName(String organizationShortName) {
		this.organizationShortName = organizationShortName;
	}
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	public MultipartFile getLogo() {
		return logo;
	}
	
	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public TimecardUserDto getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(TimecardUserDto createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public TimecardUserDto getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(TimecardUserDto modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public TimeCardPeriodDto getTimeCardPeriod() {
		return timeCardPeriod;
	}
	public void setTimeCardPeriod(TimeCardPeriodDto timeCardPeriod) {
		this.timeCardPeriod = timeCardPeriod;
	}
	public TimecardUserDto getSiteAdmin() {
		return siteAdmin;
	}
	public void setSiteAdmin(TimecardUserDto siteAdmin) {
		this.siteAdmin = siteAdmin;
	}
	public Set<TimecardUserDto> getSiteManagers() {
		return siteManagers;
	}
	public void setSiteManagers(Set<TimecardUserDto> siteManagers) {
		this.siteManagers = siteManagers;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
	
	
	public Boolean getIsLogsSaved() {
		return isLogsSaved;
	}
	public void setIsLogsSaved(Boolean isLogsSaved) {
		this.isLogsSaved = isLogsSaved;
	}
	public Boolean getIsUserLogsSaved() {
		return isUserLogsSaved;
	}
	public void setIsUserLogsSaved(Boolean isUserLogsSaved) {
		this.isUserLogsSaved = isUserLogsSaved;
	}
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public Integer getTotalOrganizations() {
		return totalOrganizations;
	}
	public void setTotalOrganizations(Integer totalOrganizations) {
		this.totalOrganizations = totalOrganizations;
	}
	/*
	 * Added By Bhagya on September 10,2014
	 * Method To follow Organization dto
	 */
	public static OrganizationDto populateOrganizationDto(Organization organization){
		OrganizationDto organizationDto=new OrganizationDto();
		if(null!=organization.getOrganizationId()){
			organizationDto.setOrganizationId(organization.getOrganizationId());
		}
		organizationDto.setOrganizationName(organization.getOrganizationName());
		organizationDto.setOrganizationShortName(organization.getOrganizationShortName());
		organizationDto.setLogoPath(organization.getLogoPath());
		organizationDto.setIsActive(organization.getIsActive());
		organizationDto.setIsLogsSaved(organization.getIsLogsSaved());
		organizationDto.setIsUserLogsSaved(organization.getIsUserLogsSaved());
		
		if(null!=organization.getTotalOrganizations()){
			organizationDto.setTotalOrganizations(organization.getTotalOrganizations());
		}
		
		if(null!=organization.getCreatedDate()){
			organizationDto.setCreatedDate(organization.getCreatedDate());
		}
		if(null!=organization.getCreatedBy()){
			organizationDto.setCreatedBy(TimecardUserDto.populateTimeCardUser(organization.getCreatedBy()));
		}
		if(null!=organization.getModifiedDate()){
			organizationDto.setModifiedDate(organization.getModifiedDate());
		}
		if(null!=organization.getModifiedBy()){
			organizationDto.setModifiedBy(TimecardUserDto.populateTimeCardUser(organization.getModifiedBy()));
		}
		organizationDto.setTheme(organization.getTheme());
		if(null!=organization.getTimeCardPeriod()){
			organizationDto.setTimeCardPeriod(TimeCardPeriodDto.populateTimecardPeriodDto(organization.getTimeCardPeriod()));
		}
		if(null!=organization.getSiteAdmin()){
			organizationDto.setSiteAdmin(TimecardUserDto.populateTimeCardUser(organization.getSiteAdmin()));
		}
		organizationDto.setStatus(organization.getStatus());
		if(null!=organization.getStatusDate()){
			organizationDto.setStatusDate(organization.getStatusDate());
		}
		
		
		if(!organization.getSiteManagers().isEmpty()){
			Set<TimecardUserDto> timecardUsers=new HashSet<TimecardUserDto>();
			for(TimecardUser user:organization.getSiteManagers()){
				TimecardUserDto userDto=TimecardUserDto.populateTimeCardUser(user);
				timecardUsers.add(userDto);
			}
			organizationDto.setSiteManagers(timecardUsers);
		}
		
		
		
		return organizationDto;
	}
	
	
}