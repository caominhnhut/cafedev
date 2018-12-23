package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Assignment;

public interface AssignmentRepository {
	
	public List<Assignment> findByUserId(Long id);
}
