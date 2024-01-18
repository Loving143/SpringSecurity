package com.smart.service;

import com.smart.entity.User;
import com.smart.request.RegisterUserRequest;

public interface UserService { 
	
	User registerUser(RegisterUserRequest request) throws Exception;

	void saveUserVerificationToken(String verificationToken , User theUser);

	String validateToken(String token);

	User getUserById(Integer id); 

}
