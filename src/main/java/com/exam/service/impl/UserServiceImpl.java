package com.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.logger.LogWritter;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user, List<UserRole> userRoles) throws Exception {
		
		String username = user.getUsername();
			
		if(userRepository.findByUsername(username) != null) {
			LogWritter.WriteToLog("User is already there");
			return null;
		}
		
		try {
			
			//saving role
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			//assign all userRoles to user object
			user.getUserRoles().addAll(userRoles);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		return user;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean deleteById(Long id) {
		
		try {
			userRepository.deleteById(id);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
