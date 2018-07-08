package com.cafedev.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/rest/")
public class HelloController {

	@RequestMapping( value = "hello")
	@ResponseBody
	public String hello(){
		return "I am spring boot";
	}
}
