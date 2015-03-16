package org.kns.timecard.backend.organization.organization.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;
import org.kns.timecard.backend.timecarduser.model.TimecardUser;


/**
 * 
 * @author JEEVAN
 * Created by Jeevan on September 09, 2014
 * Model for Organization
 *
 */

@Entity
@Table(name="kns_timecard_organization")
public class Organization implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="organization_id")
	private Integer organizationId;

	@Column(name="organization_name")
	private String organizationName;
	
	@Column(name="organization_short_name",nullable=false)
	private String organizationShortName;

	
	@Column(name="organization_logo")
	private String logoPath;
	
	@Column(name="created_date",nullable=false)
	private Date createdDate;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="created_by",nullable=false)
	private TimecardUser createdBy;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="modified_by")
	private TimecardUser modifiedBy;
	
	@Column(name="theme")
	private String 	theme;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="timecard_period")
	private TimeCardPeriod timeCardPeriod;
	
	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="site_admin")
	private TimecardUser siteAdmin;
	
	@ManyToMany(cascade={CascadeType.PERSIST})
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)	
	@JoinTable(name="kns_timecard_organization_managers",
		joinColumns={@JoinColumn(name="organization_id")},
		inverseJoinColumns={@JoinColumn(name="user_id")}
	)	
	private Set<TimecardUser> siteManagers =new HashSet<TimecardUser>();
	
	


	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="is_logs_saved")
	private Boolean isLogsSaved;
	
	@Column(name="is_user_logs_saved")
	private Boolean isUserLogsSaved;
	
	@Column(name="status")
	private String status;
	
	
	@Column(name="status_change_date") //When to implement that status change
	private Date statusDate;
	
	
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

	private Integer totalOrganizations;
	
	
	
	
	/*@Fetch(FetchMode.JOIN)
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinTable(name="kns_timecard_organization_managers",
				joinColumns={@JoinColumn(name="organization_id")},
				inverseJoinColumns={@JoinColumn(name="user_id")}	
			)
	private Set<TimecardUser> siteManagers=new HashSet<TimecardUser>();
*/
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public TimecardUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TimecardUser createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public TimecardUser getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(TimecardUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public TimeCardPeriod getTimeCardPeriod() {
		return timeCardPeriod;
	}

	public void setTimeCardPeriod(TimeCardPeriod timeCardPeriod) {
		this.timeCardPeriod = timeCardPeriod;
	}

	public TimecardUser getSiteAdmin() {
		return siteAdmin;
	}

	public void setSiteAdmin(TimecardUser siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

	public Set<TimecardUser> getSiteManagers() {
		return siteManagers;
	}

	public void setSiteManagers(Set<TimecardUser> siteManagers) {
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

	public Integer getTotalOrganizations() {
		return totalOrganizations;
	}

	public void setTotalOrganizations(Integer totalOrganizations) {
		this.totalOrganizations = totalOrganizations;
	}
	
	
	
}
