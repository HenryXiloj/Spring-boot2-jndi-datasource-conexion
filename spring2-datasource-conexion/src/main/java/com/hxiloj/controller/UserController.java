package com.hxiloj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hxiloj.model.User;
import com.hxiloj.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("user")
	public Iterable<User> findAllUser(){
		return userService.getUser();
	}

}
