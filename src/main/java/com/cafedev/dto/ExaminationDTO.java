package com.cafedev.dto;

import com.cafedev.model.Examination;

public class ExaminationDTO {
	private Long idExamination;

	private String title;

	public void copyFrom(Examination exam){
		this.idExamination = exam.getId();
		this.title = exam.getTitle();
	}

	public Long getIdExamination() {
		return idExamination;
	}

	public void setIdExamination(Long idExamination) {
		this.idExamination = idExamination;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ExaminationDTO() {
		super();
	}
	
	
}
