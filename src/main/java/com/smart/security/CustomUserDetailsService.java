package com.smart.security;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smart.entity.User;
import com.smart.repositories.UserRepositories;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

	@Autowired
	private UserRepositories userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User u =userRepo.findByEmail(username).orElseThrow(() -> new EntityNotFoundException("User not found with this exception ")); 
		return new CustomUserDetail(u);
	}

}
