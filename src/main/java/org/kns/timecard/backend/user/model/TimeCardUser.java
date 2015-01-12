package org.kns.timecard.backend.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/*
 * Created by Jeevan on June 11, 2014
 * Model for TimeCard User
 * Contians all User Credentials along with their Roles
 * 
 * Modifications
 * 1. on June 24, 2014 added fields related to Password Token
 * 2. on June 30, 2014 added fields related to first Login * 
 * 
 */


@Entity
@Table(name="kns_timecard_user")
public class TimeCardUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="username",nullable=false,unique=true,length=20)
	private String username;
	
	@Column(name="password",nullable=false,length=20)
	private String password;
	
	@Column(name="email",nullable=false,length=50)
	private String email;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private TimeCardUser createdBy;
	
	@ManyToOne
	@JoinColumn(name="modified_by")
	private TimeCardUser modifiedBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="kns_timecard_user_roles",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="role_id")})
	private Set<Roles> role=new HashSet<Roles>();
	
	@Column(name="is_enabled")
	private Boolean isEnabled;
	
	//May be Used for Payment Subscription
	@Column(name="is_active")
	private Boolean isActive;
	
	
	@Column(name="account_expiry_date")
	private Date accountExpiryDate;
	
	//added on June 24,2014 as req for gorgot pwd
	@Column(name="password_token",length=100)
	private String passwordToken;
	
	@Column(name="token_expiry")
	private Date tokenExpiryDate;
	
	
	@Column(name="profile_pic")
	private String profilePic;
	

	//added on June 30, 2014
	@Column(name="is_first_login")
	private Boolean isFirstLogin;
	
	
	public Date getTokenExpiryDate() {
		return tokenExpiryDate;
	}

	public void setTokenExpiryDate(Date tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public TimeCardUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TimeCardUser createdBy) {
		this.createdBy = createdBy;
	}

	public TimeCardUser getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(TimeCardUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set<Roles> getRole() {
		return role;
	}

	public void setRole(Set<Roles> role) {
		this.role = role;
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
	
	
	
}
