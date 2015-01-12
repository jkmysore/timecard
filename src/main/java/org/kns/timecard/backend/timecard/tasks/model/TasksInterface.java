package org.kns.timecard.backend.timecard.tasks.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * Created by Jeevan on June 17, 2014
 * Model to simiulate Tasks Excel Load
 */

@Entity
@Table(name="kns_timecard_task_interface")
public class TasksInterface implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;

	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="project_id")
	private Integer projectId;
	
	
	@Column(name="project_number")
	private String projectNumber;
	
	@Column(name="task_number")
	private String taskNumber;

	@Column(name="task_name")
	private String taskName;

	@Column(name="parent_task")
	private Integer parentTask;
	
	@Column(name="parent_task_number")
	private String parentTaskNumber;

	@Column(name="dependant_task")
	private Integer dependantTask;

	@Column(name="dependant_task_number")
	private String dependantTaskNumber;

	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="completion_date")
	private Date completionDate;
	
	@Column(name="actual_start_date")
	private Date actualStartDate;
	
	@Column(name="actual_end_date")
	private Date actualEndDate;
	
	@Column(name="is_uploaded")
	private Boolean isUploaded;
	
	@Column(name="error_message")
	private String errorMessage;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
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

	public Integer getParentTask() {
		return parentTask;
	}

	public void setParentTask(Integer parentTask) {
		this.parentTask = parentTask;
	}

	public String getParentTaskNumber() {
		return parentTaskNumber;
	}

	public void setParentTaskNumber(String parentTaskNumber) {
		this.parentTaskNumber = parentTaskNumber;
	}

	public Integer getDependantTask() {
		return dependantTask;
	}

	public void setDependantTask(Integer dependantTask) {
		this.dependantTask = dependantTask;
	}

	public String getDependantTaskNumber() {
		return dependantTaskNumber;
	}

	public void setDependantTaskNumber(String dependantTaskNumber) {
		this.dependantTaskNumber = dependantTaskNumber;
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

	public Boolean getIsUploaded() {
		return isUploaded;
	}

	public void setIsUploaded(Boolean isUploaded) {
		this.isUploaded = isUploaded;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	


}
