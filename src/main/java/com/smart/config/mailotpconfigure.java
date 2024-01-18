package com.smart.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;



public class mailotpconfigure {
	@Value("$spring.mail.host")
	private String mailHost;
	
	@Value("$spring.mail.port")
	private Integer mailPort;
	
	@Value("$spring.mail.username")
	private String mailUserName;
	
	@Value("$spring.mail.password")
	private String mailPassword;
	
	@Bean
	public JavaMailSender getJavaMailSender()
	{
		JavaMailSenderImpl javamailSender =new JavaMailSenderImpl();
		Properties props = javamailSender.getJavaMailProperties();
		props.put("mail.smtp.starttls.enable", "true"); 
		javamailSender.setHost(mailHost);
		javamailSender.setPort(mailPort);
		javamailSender.setUsername(mailUserName);
		return javamailSender;
		
	}
	
}
