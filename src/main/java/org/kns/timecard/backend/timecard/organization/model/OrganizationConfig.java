package org.kns.timecard.backend.timecard.organization.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/*
 * Created by Jeevan on June 16, 2014
 * Model to Preserver all Configuration Parameters of Organization
 * Followed as per the DB Schema of Bakta sir.
 * Saved Config details in Key -value type of structure to facilitate more customization options in future.
 */
@Entity
@Table(name="kns_timecard_organization_config")
public class OrganizationConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="config_id")
	private Integer configId;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="organization_id")
	private Organization organization;
	
	@Column(name="name")
	private String Name;
	
	@Column(name="description")
	private String description;

	@Column(name="value")
	private String value;

	@Column(name="unit_of_measure")
	private String unitOfMeasure;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
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
