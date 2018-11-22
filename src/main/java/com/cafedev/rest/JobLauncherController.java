package com.cafedev.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/")
public class JobLauncherController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("simpleJob")
	Job simpleJob;
	
	@Autowired
	@Qualifier("importUserJob")
	Job importUser;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("no-auth/launchjob")
	public String handle(){
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
			jobLauncher.run(simpleJob, jobParameters);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "Done";
	}
	
	@RequestMapping("no-auth/import-user")
	public String importStudent() throws Exception{
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
			jobLauncher.run(importUser, jobParameters);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "Done";
	}
}
