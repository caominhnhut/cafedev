package com.cafedev.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafedev.interceptor.RESTIterceptor;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	RESTIterceptor RESTIterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(RESTIterceptor);
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/.well-known/**")
                .addResourceLocations("file:/opt/jwt-demo/.well-known/");
    }
}
