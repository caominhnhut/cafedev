package com.cafedev.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;

import com.cafedev.model.Student;
import com.cafedev.step.ProcessorStudent;
import com.cafedev.step.ReaderStudent;
import com.cafedev.step.WriterStudent;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:configurationproperties.properties")
public class BatchConfigStudent {

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${import-file}")
	String fileName;
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("step-1")
				.<Student, Student>chunk(1)
				.reader(new ReaderStudent(new FileSystemResource(fileName)))
				.processor(new ProcessorStudent())
				.writer(new WriterStudent())
				.build();
	}
	
	@Bean(name="importStudentJob")
	public Job job(){
		return jobBuilderFactory.get("accounting-job")
				.incrementer(new RunIdIncrementer())
				.start(step())
				.build();
	}
	
	
}
