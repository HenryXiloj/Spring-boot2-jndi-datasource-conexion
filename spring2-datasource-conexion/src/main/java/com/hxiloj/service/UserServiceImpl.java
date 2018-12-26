package com.hxiloj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hxiloj.model.User;
import com.hxiloj.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Iterable<User> getUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}
