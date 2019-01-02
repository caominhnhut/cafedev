package com.cafedev.batchstep;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public abstract class WriterGeneric<T> implements ItemWriter<T>{

	@Override
	public void write(List<? extends T> items) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
