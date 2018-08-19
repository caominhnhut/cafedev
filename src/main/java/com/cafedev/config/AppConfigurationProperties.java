package com.cafedev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Component
@PropertySource("classpath:configurationproperties.properties")
@ConfigurationProperties
public class AppConfigurationProperties {

	@Value("${max-result}")
	private int maxResult;

	@Value("${sort-value}")
	private String sortValue;
	
	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
}
