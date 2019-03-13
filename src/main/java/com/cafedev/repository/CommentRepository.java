package com.cafedev.repository;

import java.util.List;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Comment;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface CommentRepository {
	List<Comment> findByFeedId(RequestDTO<Long> request);
	Comment save(Comment comment);
	int countListCommentByDate();
}
