package com.cafedev.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.service.CommentService;

@RestController
@RequestMapping(value="/rest/",produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(method=RequestMethod.GET, value="no-auth/comment/count-list-comments")
	public int countListCommentByDate() {
		return commentService.countListCommentByDate();
	}
}
