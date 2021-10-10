package com.casierni.todo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.casierni.todo.entity.User;
import com.casierni.todo.web.dto.UserRegistrationDto;



public interface UserService extends UserDetailsService{	
	User save(UserRegistrationDto registrationDto);
	User findByUsername(String username);
}
