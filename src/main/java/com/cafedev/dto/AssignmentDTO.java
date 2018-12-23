package com.cafedev.dto;

import com.cafedev.model.Assignment;

public class AssignmentDTO {
	private Long id; 
	private String title;
	
	public void coppyAssignment(Assignment assignment) {
		this.id = assignment.getId();
		this.title = assignment.getTitle();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
