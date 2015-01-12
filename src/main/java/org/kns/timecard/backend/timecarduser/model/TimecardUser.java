package org.kns.timecard.backend.timecarduser.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author KNS-ACCONTS
 * Created by Jeevan on September 09, 2014
 * Model for Time card User
 *
 */
@Entity
@Table(name="kns_timecard_user")
public class TimecardUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer userId;
	
	@OneToOne
	@JoinColumn(name="kns_timecard_credentials_id")
	private TimeCardUserCredentials timeCardCredentials;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="created_by")
	private TimecardUser createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="modified_by")
	private TimecardUser modifiedBy;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifedDate;
	
	
	@Column(name="account_expiry_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date accountExpiryDate;
	
	
	@Column(name="password_token")
	private String passwordToken;
	
	@Column(name="profile_pic")
	private String profilePic;
	
	@Column(name="is_first_login")
	private Boolean isFirstLogin;
	
	
	@Column(name="date_of_birth")
	private Date dateofBirth;

	//For Enabling Account using Email
	@Column(name="is_enabled")
	private Boolean isEnabled;
	
	
	//used for Plans subscription
	@Column(name="is_active")
	private Boolean isActive;
	
	//Locking account for admin
	@Column(name="is_locked")
	private Boolean isLocked;
	
	
	@Column(name="token_expiry_date")
	private Date tokenExpiryDate;
	
	
	public Integer getUserId() {
		return userId;
	}

	public Date getTokenExpiryDate() {
		return tokenExpiryDate;
	}

	public void setTokenExpiryDate(Date tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public TimecardUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TimecardUser createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public TimecardUser getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(TimecardUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifedDate() {
		return modifedDate;
	}

	public void setModifedDate(Date modifedDate) {
		this.modifedDate = modifedDate;
	}

	public Date getAccountExpiryDate() {
		return accountExpiryDate;
	}

	public void setAccountExpiryDate(Date accountExpiryDate) {
		this.accountExpiryDate = accountExpiryDate;
	}

	public String getPasswordToken() {
		return passwordToken;
	}

	public void setPasswordToken(String passwordToken) {
		this.passwordToken = passwordToken;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public TimeCardUserCredentials getTimeCardCredentials() {
		return timeCardCredentials;
	}

	public void setTimeCardCredentials(TimeCardUserCredentials timeCardCredentials) {
		this.timeCardCredentials = timeCardCredentials;
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	
	
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	
}
