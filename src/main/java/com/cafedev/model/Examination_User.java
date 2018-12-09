package com.cafedev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "EXAMINATION_USER")
public class Examination_User {
	@EmbeddedId
	private ExaminationUserId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("examinationId")
	private Examination examination;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;

	@Column(name = "path")
	private String path;

	@Column(name = "deadline")
	private Date deadline;

	public ExaminationUserId getId() {
		return id;
	}

	public void setId(ExaminationUserId id) {
		this.id = id;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Examination_User() {
		super();
	}

}
