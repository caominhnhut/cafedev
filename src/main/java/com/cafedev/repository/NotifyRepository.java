package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Notify;

public interface NotifyRepository {

	public List<Notify> findByUserId(Long userId);
	
	public Notify add(Notify notify);
	
	public int cont(Long userId);
	
}
