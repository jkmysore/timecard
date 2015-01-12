package org.kns.timecard.backend.timecard.budget.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Created by Jeevan on June 18, 2014
 * Model for Budget Interface to get data from Excel Files
 */

public class BudgetInterface implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
		
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="project_id")
	private Integer projectId;
	
	
	@Column(name="budget_id")
	private Integer budgetId;
	
	@Column(name="project_number")
	private String projectNumber;
	
	@Column(name="task_id")
	private Integer taskId;
	
	@Column(name="task_number")
	private String taskNumber;

	@Column(name="employee_id")
	private Integer employeeId;

	@Column(name="employee_number")
	private String employeeNumber;

	@Column(name="is_current")
	private Boolean isCurrent;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="estimated_hours")
	private Double estimatedHours;
	
	@Column(name="estimated_cost")
	private Double estimatedCost;
	
	@Column(name="estimated_revenue")
	private Double estimatedRevenue;
	
	@Column(name="is_budget_exceed_allowed")
	private Boolean isBudgetExceedAllowed;
	
	@Column(name="is+original_change_order")
	private Boolean isOriginalChangeOrder;
	
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

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(Double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Double getEstimatedRevenue() {
		return estimatedRevenue;
	}

	public void setEstimatedRevenue(Double estimatedRevenue) {
		this.estimatedRevenue = estimatedRevenue;
	}

	public Boolean getIsBudgetExceedAllowed() {
		return isBudgetExceedAllowed;
	}

	public void setIsBudgetExceedAllowed(Boolean isBudgetExceedAllowed) {
		this.isBudgetExceedAllowed = isBudgetExceedAllowed;
	}

	public Boolean getIsOriginalChangeOrder() {
		return isOriginalChangeOrder;
	}

	public void setIsOriginalChangeOrder(Boolean isOriginalChangeOrder) {
		this.isOriginalChangeOrder = isOriginalChangeOrder;
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
