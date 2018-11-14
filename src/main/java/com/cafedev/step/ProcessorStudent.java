package com.cafedev.step;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;

import com.cafedev.model.Student;

public class ProcessorStudent implements ItemProcessor<Student, Student>{

	@Override
	public Student process(Student student) throws Exception {
		if(student.getAccount().compareTo(new BigDecimal(0))==0){
			student.getAccount().add(new BigDecimal(100));
		}
		return student;
	}

}
