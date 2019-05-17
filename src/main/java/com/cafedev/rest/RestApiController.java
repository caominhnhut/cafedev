package com.cafedev.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cafedev.model.User;

public abstract class RestApiController {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected User getAuthenticatedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user;
	}
}
