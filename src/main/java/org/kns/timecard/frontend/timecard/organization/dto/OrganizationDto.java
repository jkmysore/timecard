package org.kns.timecard.frontend.timecard.organization.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.kns.timecard.backend.timecard.organization.model.Organization;
import org.kns.timecard.backend.user.model.TimeCardUser;
import org.kns.timecard.frontend.user.dto.TimecardUserDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *	
 *Created on June 26, 2014
 *DTO for organization
 *
 */
public class OrganizationDto {

	
	private Integer organizationId;
	
	@NotNull
	@NotBlank
	private String organizationName;
	
	@NotNull
	@NotBlank
	private String organizationShortName;
	private String logoPath;
	private MultipartFile logo;
	
	
	private Integer weekEndingDay;
	
	
	private Double minHoursPerWeek;
	
	
	private Double maxHoursPerWeek;
	private String theme;
	private String whineList;
	
	private Date createdDate;
	private TimecardUserDto createdUser;
	private Date modifiedBy;
	private TimecardUserDto modifiedUser;
	
	private TimecardPeriodDto timecardPeriod;
	
	private String siteAdminUserName;
	
	private String siteAdminEmail;
	
	private String siteAdminPassword;
	
	private TimecardUserDto siteAdmin;
	
	private Set<TimecardUserDto> siteManagers; 
	
	
	
	public String getSiteAdminUserName() {
		return siteAdminUserName;
	}
	public void setSiteAdminUserName(String siteAdminUserName) {
		this.siteAdminUserName = siteAdminUserName;
	}
	
	public String getSiteAdminEmail() {
		return siteAdminEmail;
	}
	public void setSiteAdminEmail(String siteAdminEmail) {
		this.siteAdminEmail = siteAdminEmail;
	}
	public String getSiteAdminPassword() {
		return siteAdminPassword;
	}
	public void setSiteAdminPassword(String siteAdminPassword) {
		this.siteAdminPassword = siteAdminPassword;
	}
	
	public TimecardUserDto getSiteAdmin() {
		return siteAdmin;
	}
	public void setSiteAdmin(TimecardUserDto siteAdmin) {
		this.siteAdmin = siteAdmin;
	}
	public TimecardPeriodDto getTimecardPeriod() {
		return timecardPeriod;
	}
	public void setTimecardPeriod(TimecardPeriodDto timecardPeriod) {
		this.timecardPeriod = timecardPeriod;
	}
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
	public Integer getWeekEndingDay() {
		return weekEndingDay;
	}
	public void setWeekEndingDay(Integer weekEndingDay) {
		this.weekEndingDay = weekEndingDay;
	}
	
	public Double getMinHoursPerWeek() {
		return minHoursPerWeek;
	}
	public void setMinHoursPerWeek(Double minHoursPerWeek) {
		this.minHoursPerWeek = minHoursPerWeek;
	}
	public Double getMaxHoursPerWeek() {
		return maxHoursPerWeek;
	}
	public void setMaxHoursPerWeek(Double maxHoursPerWeek) {
		this.maxHoursPerWeek = maxHoursPerWeek;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getWhineList() {
		return whineList;
	}
	public void setWhineList(String whineList) {
		this.whineList = whineList;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public TimecardUserDto getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(TimecardUserDto createdUser) {
		this.createdUser = createdUser;
	}
	public Date getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Date modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public TimecardUserDto getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(TimecardUserDto modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	
	
	
	
	public Set<TimecardUserDto> getSiteManagers() {
		return siteManagers;
	}
	public void setSiteManagers(Set<TimecardUserDto> siteManagers) {
		this.siteManagers = siteManagers;
	}
	public static OrganizationDto populateOrganizationDto(Organization organization){
		OrganizationDto organizationDto=new OrganizationDto();
		
		organizationDto.setLogoPath(organization.getLogoPath());
		organizationDto.setOrganizationShortName(organization.getOrganizationShortName());
		organizationDto.setTheme(organization.getTheme());
		organizationDto.setWhineList(organization.getWhineList());
		if(null!=organization.getMaxHoursPerWeek()){
			organizationDto.setMaxHoursPerWeek(organization.getMaxHoursPerWeek());
		}
		if(null!=organization.getMinHoursPerWeek()){
			organizationDto.setMinHoursPerWeek(organization.getMinHoursPerWeek());
		}
		if(null!=organization.getWeekEndingDay()){
			organizationDto.setWeekEndingDay(organization.getWeekEndingDay());
		}
		if(null!=organization.getCreatedBy()){
			organizationDto.setCreatedUser(TimecardUserDto.populateTimeCardUser(organization.getCreatedBy()));
		}
		if(null!=organization.getCreatedDate()){
			organizationDto.setCreatedDate(organization.getCreatedDate());
		}
		if(null!=organizationDto.getModifiedBy()){
			organizationDto.setModifiedUser(TimecardUserDto.populateTimeCardUser(organization.getModifiedBy()));
		}
		if(null!=organization.getTimeCardPeriod()){
			organizationDto.setTimecardPeriod(TimecardPeriodDto.populateTimecardPeriodDto(organization.getTimeCardPeriod()));
		}
		
		if(null!=organization.getSiteAdmin()){
			organizationDto.setSiteAdmin(TimecardUserDto.populateTimeCardUser(organization.getSiteAdmin()));
		}
		
		if(!organization.getSiteManagers().isEmpty()){
			Set<TimecardUserDto> timecardUsers=new HashSet<TimecardUserDto>();
			for(TimeCardUser user:organization.getSiteManagers()){
				TimecardUserDto userDto=TimecardUserDto.populateTimeCardUser(user);
				timecardUsers.add(userDto);
			}
			organizationDto.setSiteManagers(timecardUsers);
		}
		
		
		
		return organizationDto;
	}
	
	
	
	
	
	
	
}
