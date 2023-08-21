package com.exam.service;

import java.util.List;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	
	//create user
	public User createUser(User user, List<UserRole> userRoles) throws Exception;
	
	//get User by Username
	public User findByUsername(String username);

	public boolean deleteById(Long id);

}
