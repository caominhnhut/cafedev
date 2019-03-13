package com.cafedev.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.common.MessageConst;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Comment;
import com.cafedev.model.Feed;
import com.cafedev.model.User;
import com.cafedev.repository.CommentRepository;
import com.cafedev.repository.FeedRepository;
import com.cafedev.repository.UserRepository;
import com.cafedev.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
		
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseDTO<Comment> saveComment(Comment comment) {
		ResponseDTO<Comment> response = new ResponseDTO<Comment>();
		boolean isValid = checkValid(comment, response);
		if (isValid) {
			comment.setCreateDate(new Date());
			Comment commentResult = commentRepository.save(comment);
			response.setData(commentResult);
		}
		return response;
	}
	
	private boolean checkValid(Comment comment, ResponseDTO<Comment> response) {
		List<Feed> lstFeed = feedRepository.findFeedById(comment.getFeed().getId());
		List<User> lstUser = userRepository.findUserById(comment.getUser().getId());
		if (comment.getFeed().getId() == null) {
			response.setErrorMessage(MessageConst.ERROR_FEED_ID_EMPTY);
			return false;
		}
		else if (comment.getUser().getId() == null) {
			response.setErrorMessage(MessageConst.ERROR_USER_ID_EMPTY);
			return false;
		}
		else if (lstFeed.size() ==0) {
			response.setErrorMessage(MessageConst.ERROR_FEED_ID_NOT_EXIST);
			return false;
		}
		else if (lstUser.size() ==0) {
			response.setErrorMessage(MessageConst.ERROR_USER_ID_NOT_EXIST);
			return false;
		}
		return true;
	}
	
	@Override
	public int countListCommentByDate() {
		return commentRepository.countListCommentByDate();
	}

}
