package com.anirudh.Noted.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anirudh.Noted.model.User;
import com.anirudh.Noted.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public User saveUser(User user) { 
		
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
}