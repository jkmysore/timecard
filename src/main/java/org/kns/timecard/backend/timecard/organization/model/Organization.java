package org.kns.timecard.backend.timecard.organization.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.kns.timecard.backend.user.model.TimeCardUser;

/*
 * Created by Jeevan on June 16, 2014
 * Model class for Organization
 */
@Entity
@Table(name="kns_timecard_organization")
public class Organization implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	@Column(name="organization_id")
	private Integer id;
	
	@Column(name="organization_name",nullable=false)
	private String organizationName;
	
	@Column(name="organization_short_name",nullable=false)
	private String organizationShortName;

	
	@Column(name="organization_logo")
	private String logoPath;
	
	@Column(name="created_date",nullable=false)
	private Date createdDate;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="created_by",nullable=false)
	private TimeCardUser createdBy;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="modified_by")
	private TimeCardUser modifiedBy;
	
	@Column(name="theme")
	private String theme;

	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="timecard_period")
	private TimeCardPeriod timeCardPeriod;
	
	@Column(name="week_ending_day")
	private Integer weekEndingDay;
	
	@Column(name="min_hours_per_week")
	private Double minHoursPerWeek;
	
	@Column(name="max_hours_per_week")
	private Double maxHoursPerWeek;
	
	@Column(name="whine_list")
	private String whineList;
	
	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="site_admin")
	private TimeCardUser siteAdmin;
	
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinTable(name="kns_timecard_organization_managers",
				joinColumns={@JoinColumn(name="organization_id")},
				inverseJoinColumns={@JoinColumn(name="user_id")}	
			)
	private Set<TimeCardUser> siteManagers=new HashSet<TimeCardUser>();
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public TimeCardUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TimeCardUser createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public TimeCardUser getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(TimeCardUser modifiedBy) {
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

	public String getWhineList() {
		return whineList;
	}

	public void setWhineList(String whineList) {
		this.whineList = whineList;
	}

	public TimeCardUser getSiteAdmin() {
		return siteAdmin;
	}

	public void setSiteAdmin(TimeCardUser siteAdmin) {
		this.siteAdmin = siteAdmin;
	}

	public Set<TimeCardUser> getSiteManagers() {
		return siteManagers;
	}

	public void setSiteManagers(Set<TimeCardUser> siteManagers) {
		this.siteManagers = siteManagers;
	}

	
	
	
}
