package com.cafedev.step;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.cafedev.model.Student;

public class ReaderStudent extends FlatFileItemReader<Student>{
	
	public ReaderStudent(Resource resource){
		super();
		setResource(resource);
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[]{"userId","name","dept","account"});
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		
		BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<Student>();
		fieldSetMapper.setTargetType(Student.class);
		
		DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<Student>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(defaultLineMapper);
	};
}
