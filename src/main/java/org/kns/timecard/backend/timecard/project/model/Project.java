package org.kns.timecard.backend.timecard.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.kns.timecard.backend.timecard.organization.model.Organization;
import org.kns.timecard.backend.user.model.TimeCardUser;

/*
 * Created by Jeevan on June 17, 2014
 * Model to simulate Projects Table
 */
@Entity
@Table(name="kns_timecard_project")
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="project_id")
	private Integer projectId;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id",nullable=false)
	private Organization organization;
	
	@Column(name="project_number",length=30)
	private String projectNumber;
	
	@Column(name="project_name",length=255)
	private String projectName;
	
	@Column(name="project_status",length=1)
	private String projectStatus;
	
	@Column(name="start_date")
	private Date startDate;

	@Column(name="completion_date")
	private Date completionDate;
	
	@Column(name="is_billable")
	private Boolean isBillable;
	
	@Column(name="is_price_fixed")
	private Boolean isPriceFixed;
	
	
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="created_by")
	private TimeCardUser createdBy;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="modified_by")
	private TimeCardUser modifiedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	

}
