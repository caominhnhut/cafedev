package com.cafedev.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.cafedev.model.Student;

public class WriterStudent implements ItemWriter<Student>{

	@Override
	public void write(List<? extends Student> students) throws Exception {
		for(Student student:students){
			System.out.println("Writting for: "+student);
		}
	}

}
