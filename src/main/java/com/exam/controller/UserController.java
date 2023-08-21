package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userService;	 
	
	//CREATE USER
	@PostMapping("/") //CHANGE
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		try {
			Role role = new Role();
			role.setRoleId(45L);
			role.setRoleName("NORMAL");
			
			List<UserRole> userRoles = new ArrayList<UserRole>();
			
			userRoles.add(new UserRole(user, role));
			
			User addedUser = userService.createUser(user, userRoles);
			
			if(addedUser != null) {
				return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(addedUser, HttpStatus.PRECONDITION_FAILED);
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//GET USER BY USERNAME
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username){
		try {
			User user = userService.findByUsername(username);
			
			if(user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET USER BY USERNAME
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		try {
			boolean checkDelete = userService.deleteById(id);
			
			if(checkDelete) {
				return new ResponseEntity<>("Deleted user: " + id, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
