package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.logger.LogWritter;
import com.exam.model.User;
import com.exam.repo.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsername(username);
		
		
		if(user == null) {
			LogWritter.WriteToLog("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		
		return user;
	}
}
