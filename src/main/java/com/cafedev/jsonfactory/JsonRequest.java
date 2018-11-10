package com.cafedev.jsonfactory;

import com.google.gson.Gson;

public abstract class JsonRequest{

	protected Gson gson = new Gson();
	
	public abstract String createJson();
	
}
