package com.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.exam.service.impl.UserDetailsServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
	    
	    return new UserDetailsServiceImpl();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	    return builder.getAuthenticationManager();
	}

}
