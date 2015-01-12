package org.kns.timecard.backend.timecard.tasks.model;

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
import org.kns.timecard.backend.timecard.project.model.Project;
import org.kns.timecard.backend.user.model.TimeCardUser;
import org.springframework.scheduling.config.Task;

/*
 * Created by Jeevan on June 17, 2014
 	Model for Tasks
 */
@Entity
@Table(name="kns_timecard_tasks")
public class Tasks implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="task_id")
	private Integer task_id;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="organization")
	private Organization organization;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="project_id")
	private Project project;
	
	@Column(name="task_number")
	private String taskNumber;

	@Column(name="task_name")
	private String taskName;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="parent_task_id")
	private Tasks parentTask;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="dependant_task")
	private Tasks dependantTask;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="completion_date")
	private Date completionDate;
	
	@Column(name="actual_start_date")
	private Date actualStartDate;
	
	@Column(name="actual_end_date")
	private Date actualEndDate;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="created_by")
	private TimeCardUser createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="modified_by")
	private TimeCardUser modifiedBy;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	

	public Integer getTask_id() {
		return task_id;
	}

	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}



	public Tasks getParentTask() {
		return parentTask;
	}

	public void setParentTask(Tasks parentTask) {
		this.parentTask = parentTask;
	}

	public Tasks getDependantTask() {
		return dependantTask;
	}

	public void setDependantTask(Tasks dependantTask) {
		this.dependantTask = dependantTask;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public TimeCardUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TimeCardUser createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public TimeCardUser getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(TimeCardUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


}
