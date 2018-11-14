package com.cafedev.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cafedev.step.Processor;
import com.cafedev.step.Reader;
import com.cafedev.step.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean(name="simpleJob")
	public Job job(){
		return jobBuilderFactory.get("myJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
	
	@Bean
	public Step step1(){
		return stepBuilderFactory.get("step1")
				.<String, String>chunk(1)
				.reader(new Reader())
				.processor(new Processor())
				.writer(new Writer())
				.build();
	}
}
