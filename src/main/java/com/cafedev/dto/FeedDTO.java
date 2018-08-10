package com.cafedev.dto;

import java.util.ArrayList;
import java.util.List;

import com.cafedev.model.Comment;
import com.cafedev.model.Feed;

public class FeedDTO {

	private Long id;
	
	private String description;

	private String filePath;

	private String createDate;

	private UserDTO user;
	
	private List<CommentDTO> comments = new ArrayList<CommentDTO>();

	public void copyFrom(Feed feed){
		this.id = feed.getId();
		this.description = feed.getDescription();
		this.filePath = feed.getFilePath();
		this.createDate = feed.getCreateDate();
		this.user = new UserDTO();
		this.user.copyFrom(feed.getUser());
		for(Comment cm:feed.getComments()){
			CommentDTO cmd = new CommentDTO();
			cmd.copyFrom(cm);
			this.comments.add(cmd);
		}
	}
	
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
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
