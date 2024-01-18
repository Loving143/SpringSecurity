package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.security.CustomUserDetail;
import com.smart.security.CustomUserDetailsService;

@Service
public class AuthenticationProviderServiceImpl implements AuthenticationProvider{

	@Autowired
	private CustomUserDetailsService detailsService;
	
	@Autowired
	private CustomUserDetail userDetails;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userName = authentication.getName();
		String passWord = authentication.getCredentials().toString();
		CustomUserDetail userDetail = (CustomUserDetail) detailsService.loadUserByUsername(userName);
		
		return checkPassword(userDetail, passWord , bCryptPasswordEncoder);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Authentication checkPassword(CustomUserDetail userDetail, String rawPassWord , BCryptPasswordEncoder encoder )
	{
		if(encoder.matches(rawPassWord, userDetail.getPassword()))
		{
			return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
		}
		else
		{
			throw new BadCredentialsException("Bad Credentials .Please put the valid username and password .");
		}
	}

}
