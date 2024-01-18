package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smart.entity.User;
import com.smart.service.UserService;

@RestController
public class UserManagementController {
	
	@Autowired
	private UserService userService;
	@GetMapping("/get/{id}")
	public User getUserById(@PathVariable("id") Integer id)
	{
		return userService.getUserById(id);
	}
	

}
