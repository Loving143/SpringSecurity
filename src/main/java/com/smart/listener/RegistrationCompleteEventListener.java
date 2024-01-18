package com.smart.listener;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.smart.entity.User;
import com.smart.event.RegistrationCompleteEvent;
import com.smart.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component 
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{

	@Autowired
	private final JavaMailSender mailSender;
	
	@Autowired
	private final UserService userService ;
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// TODO Auto-generated method stub
		//1. Get the newly Regsitered User.
		User theUser = event.getUser(); 
		
		//2. Create a verification Token for that User .
		String verificationToken = UUID.randomUUID().toString();
		//3. Save the verification Token for the user . 
		userService.saveUserVerificationToken(verificationToken,theUser);
		//4. Build the verification Url to be sent to the user.
		String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
		log.info("Click the link to verify your registration. :"+ url); 
		//5. We are going to actually send the email.
		try {
			sendMail("Prateek.kumar949@gmail.com",url);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void sendMail(String email,String url) throws MessagingException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setTo(email);
		mimeMessageHelper.setSubject("otp");
		mimeMessageHelper.setText("Click the link to verify your registration :"+url);
		mailSender.send(mimeMessage);
	}
	
	//This listener is going to extend the application Listener.

}
