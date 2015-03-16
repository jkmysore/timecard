package org.kns.timecard.backend.timecarduser.model;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Proxy;


/**
 * 
 * @author Jeevan
 *Created by Jeevan on September 09, 2014
 *Entity for Time card User Credentials
 *
 */

@Entity
@Table(name="kns_timecard_credentials")
public class TimeCardUserCredentials implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue	
	@Column(name="id")
	private Integer id;
		
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="kns_timecard_user_roles",
		joinColumns={
			@JoinColumn(name="credential_id")},
					inverseJoinColumns={@JoinColumn(name="role_id")
		})
	private Set<Roles> role=new HashSet<Roles>();
	
	
	public Set<Roles> getRole() {
		return role;
	}

	public void setRole(Set<Roles> role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
}
