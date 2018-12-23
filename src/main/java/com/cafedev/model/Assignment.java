package com.cafedev.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "ASSIGNMENT")
public class Assignment {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", length = 100, nullable = false)
	private String title;
	
	@Column(name = "content",nullable = false)
	private String content;
	
	
	@OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
	private List<AssignmentUser> assignmentUsers = new ArrayList<AssignmentUser>();
	
	public List<AssignmentUser> getAssignmentUsers() {
		return assignmentUsers;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
