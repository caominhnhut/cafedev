package com.cafedev.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cafedev.common.TimeProvider;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Entity
@Table(name = "FEED")
public class Feed{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "file_path", nullable = true)
	private String filePath;

	@Column(name = "create_date")
	private Date createDate;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
	private User user;
	
	@OneToMany(mappedBy = "feed", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCreateDate() {
		return TimeProvider.convertDateToString(this.createDate);
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
