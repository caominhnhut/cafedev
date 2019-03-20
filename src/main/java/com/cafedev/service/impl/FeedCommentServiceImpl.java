package com.cafedev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.dto.FeedCommentDTO;
import com.cafedev.repository.CommentRepository;
import com.cafedev.repository.FeedRepository;
import com.cafedev.service.FeedCommentService;

@Service
public class FeedCommentServiceImpl implements FeedCommentService {

	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	@Override
	public FeedCommentDTO countByDate() {
		FeedCommentDTO feedcomment = new FeedCommentDTO();
		feedcomment.setNumberFeed(feedRepository.countByDate());
		feedcomment.setNumberComment(commentRepository.countByDate());
		return feedcomment;
	}
}
