package com.cafedev.dto;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import com.cafedev.model.Comment;

public class CommentDTO {

	private Long id;

	private String content;

	private String createDate;

	private UserDTO user;

	private List<CommentDTO> subComments = new ArrayList<CommentDTO>();

	public void copyFrom(Comment comment){
		this.id = comment.getId();
		this.content = comment.getContent();
		this.createDate = comment.getCreateDate();
		this.user = new UserDTO();
		this.user.copyFrom(comment.getUser());
		for(Comment cm:comment.getSubComments()){
			CommentDTO cmd = new CommentDTO();
			cmd.setId(cm.getId());
			cmd.setContent(cm.getContent());
			cmd.setCreateDate(cm.getCreateDate());
			cmd.user = new UserDTO();
			cmd.user.copyFrom(cm.getUser());
			subComments.add(cmd);
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<CommentDTO> getSubComments() {
		return subComments;
	}
}
