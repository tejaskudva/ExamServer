package com.exam.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceAuth {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws Exception{
		
		Role role = new Role();
		role.setRoleId(55L);
		role.setRoleName("ADMIN");
		
		List<UserRole> userRoles = new ArrayList<UserRole>();
		
		userRoles.add(new UserRole(user, role));
		
		User addedUser = userService.createUser(user, userRoles);
		
		return addedUser;
	}

}
