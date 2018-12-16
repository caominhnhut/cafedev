package com.cafedev.dto;

import java.util.Date;

import com.cafedev.model.ExaminationUser;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExaminationDetailDTO {
	
	private long idExam;
	
	private long idUser;

	private String title;

	private String content;
	
	private String path;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date deadline;
	
	private boolean status;
	
	public void copyFrom(ExaminationUser eu) {
		this.idExam = eu.getExamination().getId();
		this.idUser = eu.getId().getUserId();
		this.title = eu.getExamination().getTitle();
		this.content = eu.getExamination().getContent();
		this.path = eu.getPath();
		this.deadline = eu.getDeadline();
		this.status = eu.isStatus();
	}

	public long getIdExam() {
		return idExam;
	}

	public void setIdExam(long idExam) {
		this.idExam = idExam;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ExaminationDetailDTO() {
		super();
	}

}
