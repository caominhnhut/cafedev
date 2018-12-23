package com.cafedev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.model.Assignment;
import com.cafedev.repository.AssignmentRepository;
import com.cafedev.service.AssignmentService;


@Service
public class AssignmentServiceImpl implements AssignmentService{

	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Override
	public List<Assignment> findByUserId(Long userId) {
		return assignmentRepository.findByUserId(userId);
	}

}
