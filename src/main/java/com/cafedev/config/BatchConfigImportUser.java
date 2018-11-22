package com.cafedev.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;

import com.cafedev.batchstep.importuser.ProcessorUser;
import com.cafedev.batchstep.importuser.ReaderUser;
import com.cafedev.batchstep.importuser.WriterUser;
import com.cafedev.dto.UserRequestDTO;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:configurationproperties.properties")
public class BatchConfigImportUser extends JobExecutionListenerSupport{

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${import-file}")
	String fileName;
	
	@Autowired
	ProcessorUser processorUser;
	
	@Autowired
	WriterUser writeUser;
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("step-1")
				.<UserRequestDTO, UserRequestDTO>chunk(1)
				.reader(new ReaderUser(new FileSystemResource(fileName)))
				.processor(processorUser)
				.writer(writeUser)
				.build();
	}
	
	@Bean(name="importUserJob")
	public Job job(){
		return jobBuilderFactory.get("import-job")
				.incrementer(new RunIdIncrementer())
				.start(step())
				.build();
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("ENDING");
		if(jobExecution.getStatus().equals(BatchStatus.COMPLETED)){
			System.out.println("NHUT QUA DEP TRAI");
		}
	}
	
}
