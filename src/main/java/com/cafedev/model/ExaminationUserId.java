package com.cafedev.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExaminationUserId implements Serializable {

	@Column(name = "Examination_id")
	private Long examinationId;

	@Column(name = "User_Id")
	private Long userId;

	public Long getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ExaminationUserId(Long examinationId, Long userId) {
		super();
		this.examinationId = examinationId;
		this.userId = userId;
	}

	public ExaminationUserId() {
		super();
	}

}
