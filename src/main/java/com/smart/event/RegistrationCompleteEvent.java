package com.smart.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import com.smart.entity.User;

public class RegistrationCompleteEvent extends ApplicationEvent {

	
	private User user;
	private String applicationUrl;
	
	
	public User getUser() {
		return user;
	}
	
	public String getApplicationUrl() {
		return applicationUrl;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}


	public RegistrationCompleteEvent(Object source) {
		super(source);
		
	}

	public RegistrationCompleteEvent( User user, String applicationUrl) {
		super(user);
		this.user = user;
		this.applicationUrl = applicationUrl;
	} 
	
	

}
