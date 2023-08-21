package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtHelper;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.impl.UserServiceAuth;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;
    
    @Autowired
    private UserServiceAuth userService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
    	
    	try {
    		this.doAuthenticate(request.getUsername(), request.getPassword());


            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = this.helper.generateToken(userDetails);

            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .username(userDetails.getUsername()).build();
            
            System.out.println("Generated jwt: " + token);
            return new ResponseEntity<>(response, HttpStatus.OK);
            
    	} catch(BadCredentialsException e) {
    		JwtResponse response = new JwtResponse(null, request.getUsername());		
    		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    		
    	} catch(Exception e) {
    		JwtResponse response = new JwtResponse(null, request.getUsername());
    		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    		
    	}
        
    }
    
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) throws Exception {
        
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
    }
    
    @GetMapping("/currentUser")
    public ResponseEntity<User> currentUser(Principal principal) throws Exception {
    	
        return new ResponseEntity<>((User) userDetailsService.loadUserByUsername(principal.getName()), HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
        	e.printStackTrace();
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
