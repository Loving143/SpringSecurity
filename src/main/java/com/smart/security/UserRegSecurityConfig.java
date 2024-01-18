package com.smart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserRegSecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.cors()
	                .and().csrf().disable()
	                .authorizeHttpRequests()
	                .antMatchers("/register/**")
	                .permitAll()
	                .and()
	                .authorizeHttpRequests()
	                .antMatchers("/users/**")
	                .hasAnyAuthority("USER", "ADMIN")
	                .and()
	                .authorizeHttpRequests().anyRequest().authenticated()
	             
	                .and().formLogin().and().build();
	    }

}
