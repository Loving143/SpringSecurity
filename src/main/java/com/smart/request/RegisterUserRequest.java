package com.smart.request;

public class RegisterUserRequest {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private boolean isEnabled;
	
	public RegisterUserRequest(String firstName, String lastName, String email, String password, String role,
			boolean isEnabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.isEnabled = isEnabled;
	}
	
	public RegisterUserRequest() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	
}
