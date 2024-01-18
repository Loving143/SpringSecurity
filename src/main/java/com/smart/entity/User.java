package com.smart.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.smart.request.RegisterUserRequest;

@Entity
@Table(name = "UserTable")
public class User {
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	private String password;
	private String role;
	private boolean isEnabled=false;
	
	@OneToMany(mappedBy="user" , fetch = FetchType.EAGER) 
	private List<Authority> authorities;
	
	public User(Long id, String firstName, String lastName, String email, String password, String role,
			boolean isEnabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.isEnabled = isEnabled;
	}

	public User(RegisterUserRequest request) {
		
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.email = request.getEmail();
		this.password = request.getPassword();
		this.isEnabled = request.isEnabled();
		this.role = request.getRole();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public User() {
		super();
		
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}
	

	
}
