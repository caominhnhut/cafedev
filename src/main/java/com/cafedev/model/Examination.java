package com.cafedev.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "EXAMINATION")
public class Examination {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date createDate;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Examination_User> examinationUser = new ArrayList<>();

	@JsonIgnore
	public List<Examination_User> getExaminationUser() {
		return examinationUser;
	}

	public void setExaminationUser(List<Examination_User> examinationUser) {
		this.examinationUser = examinationUser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Examination() {
		super();
	}

}
