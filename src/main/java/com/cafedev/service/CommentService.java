package com.cafedev.service;
	
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Comment;

public interface CommentService {
	ResponseDTO<Comment> saveComment(Comment comment);
}
