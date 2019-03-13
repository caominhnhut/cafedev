package com.cafedev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.repository.CommentRepository;
import com.cafedev.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public int countListCommentByDate() {
		return commentRepository.countListCommentByDate();
	}

}
