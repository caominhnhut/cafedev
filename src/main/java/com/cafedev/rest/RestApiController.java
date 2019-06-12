package com.cafedev.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cafedev.model.User;
import com.google.gson.Gson;

public abstract class RestApiController {
	
	private Gson gson;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected User getAuthenticatedUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user;
	}
	
	protected Gson getGson(){
		if(gson == null){
			gson = new Gson();
		}
		return gson;
	}
}
