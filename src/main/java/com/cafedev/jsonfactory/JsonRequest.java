package com.cafedev.jsonfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public abstract class JsonRequest{

	protected Gson gson = new Gson();
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	public abstract String createJson();
	
}
