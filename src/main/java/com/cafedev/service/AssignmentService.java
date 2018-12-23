package com.cafedev.service;

import java.util.List;

import com.cafedev.model.Assignment;

public interface AssignmentService {
	
	public List<Assignment> findByUserId(Long Id);
}
