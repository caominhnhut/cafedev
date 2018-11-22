package com.cafedev.batchstep.importuser;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.cafedev.dto.UserRequestDTO;

public class ReaderUser extends FlatFileItemReader<UserRequestDTO>{
	
	public ReaderUser(Resource resource){
		super();
		setResource(resource);
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[]{"userName","password","firstName","lastName","email","phoneNumber","avatar"});
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		
		BeanWrapperFieldSetMapper<UserRequestDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<UserRequestDTO>();
		fieldSetMapper.setTargetType(UserRequestDTO.class);
		
		DefaultLineMapper<UserRequestDTO> defaultLineMapper = new DefaultLineMapper<UserRequestDTO>();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(defaultLineMapper);
	};
}
