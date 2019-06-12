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

	@Value("${max-topic-number}")
	private int maxTopicNumber;

	@Value("${sort-value}")
	private String sortValue;

	@Value("${sort-topic-value}")
	private String sortTopicValue;

	@Value("${email-from}")
	private String emailFrom;

	@Value("${email-password}")
	private String emailPassword;

	@Value("${email-title}")
	private String emailTitle;

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

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

	public int getMaxTopicNumber() {
		return maxTopicNumber;
	}

	public void setMaxTopicNumber(int maxTopicNumber) {
		this.maxTopicNumber = maxTopicNumber;
	}

	public String getSortTopicValue() {
		return sortTopicValue;
	}

	public void setSortTopicValue(String sortTopicValue) {
		this.sortTopicValue = sortTopicValue;
	}
}
