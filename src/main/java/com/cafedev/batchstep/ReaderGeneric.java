package com.cafedev.batchstep;

import java.lang.reflect.Field;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

public abstract class ReaderGeneric<T> extends FlatFileItemReader<T> {

	private Logger log = (Logger) LogFactory.getLog(this.getClass());
	
	private Class<T> entity;
	
	public ReaderGeneric(Resource resource, Class<T> clazz) {
		
		super();
		setResource(resource);
		
		this.entity = clazz;
		log.info("Reading data from " + this.entity.getSimpleName());
		Field[] properties = this.entity.getDeclaredFields();
		String[] fields = new String[properties.length];
		
		for (int i=0; i<properties.length; i++) {
			log.info("Getting fields from " + this.entity.getSimpleName());
			fields[i] = properties[i].getName();
		}
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(fields);
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		
		BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<T>();
		fieldSetMapper.setTargetType(this.entity);
		
		DefaultLineMapper<T> defaultLineMapper = new DefaultLineMapper<T>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(defaultLineMapper);
	}
}
