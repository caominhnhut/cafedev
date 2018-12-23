package com.cafedev.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignment_user")
public class AssignmentUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Assignment assignment;
	private User user;
	
	@Id	
	@ManyToOne
	@JoinColumn(name = "assignment_id")
	public Assignment getAssignment() {
		return assignment;
	}
	
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "dealine")
	private Date dealine;
	
	@Column(name = "file")
	private String file;

	public Date getDealine() {
		return dealine;
	}

	public void setDealine(Date dealine) {
		this.dealine = dealine;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
