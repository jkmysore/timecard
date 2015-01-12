package org.kns.timecard.frontend.organization.organization.dto;

/**
 * Created by Bhagya on Septemebr 10, 2014
 * Dto for OrganizationConfig
 *
 */

public class OrganizationConfigDto{
	private Integer configId;
	private OrganizationDto organization;
	private String Name;
	private String description;
	private String value;
	private String unitOfMeasure;
	
	public Integer getConfigId() {
		return configId;
	}
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
	public OrganizationDto getOrganization() {
		return organization;
	}
	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
}