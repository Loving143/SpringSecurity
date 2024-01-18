package com.smart.token;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.smart.entity.User;

@Entity
public class VerificationToken { 
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	
	private Long id;
	private String token ;
	private Date expirationTime;
	private static  final int EXPIRATION_TIME = 15;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public VerificationToken(Long id, String token, Date expirationTime, User user) {
		super();
		this.id = id;
		this.token = token;
		this.expirationTime = expirationTime;
		this.user = user;
	}
	
	public VerificationToken(String token, User user) {
		
		super();
		this.token = token;
		this.user = user;
		this.expirationTime = this.getTokenExpirationTime();
	}
	
public VerificationToken(String token) {
		
		super();
		this.token = token;
		this.expirationTime = this.getTokenExpirationTime();
	}
	
	public Date getTokenExpirationTime()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
		return new Date(calendar.getTime().getTime());
		
	}
	
	public Long getId() {
		return id;
	}
	public String getToken() {
		return token;
	}
	public Date getExpirationTime() {
		return expirationTime;
	}
	public User getUser() {
		return user;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public VerificationToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
