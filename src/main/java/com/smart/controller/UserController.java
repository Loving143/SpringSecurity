package com.smart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart.entity.User;
import com.smart.event.RegistrationCompleteEvent;
import com.smart.request.RegisterUserRequest;
import com.smart.service.UserService;
import com.smart.token.VerificationToken;
import com.smart.token.VerificationTokenRepository;

@RestController
@RequestMapping("/register")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private VerificationTokenRepository verificationRepo;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public String registerUser(@RequestBody RegisterUserRequest request, final HttpServletRequest servletRequest) throws Exception
	{
		User user = userService.registerUser(request);
		publisher.publishEvent(new RegistrationCompleteEvent(user, ApplicationUrl(servletRequest)));
		return "Success !! Please check your email to complete registration ";
	}
	
	public String ApplicationUrl(HttpServletRequest request)
	{
		//When the user is saved successfully to the database the application is going to publish an event .
		//So we are publishing this event .When this event got published then the user will listen to this event .
		//After listening this event the user will send the email .
		return "http://"+request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
	
    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
    	VerificationToken theToken = verificationRepo.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return "This account has already been verified, please, login.";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return "Email verified successfully. Now you can login to your account";
        }
        return "Invalid verification token";
    }
}
