package org.kns.timecard.backend.timecard.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Created by Jeevan on June 17, 2014
 * Class to save all Project Details loaded from Excel File
 */
public class ProjectInterface implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="organization_id")
	private Integer organization_id;
	
	@Column(name="project_number")
	private String projectNumber;
	
	
	@Column(name="project_name")
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
	
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="customer_name")
	private String customerName;

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

	public Integer getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
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

	public Boolean getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(Boolean isBillable) {
		this.isBillable = isBillable;
	}

	public Boolean getIsPriceFixed() {
		return isPriceFixed;
	}

	public void setIsPriceFixed(Boolean isPriceFixed) {
		this.isPriceFixed = isPriceFixed;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

