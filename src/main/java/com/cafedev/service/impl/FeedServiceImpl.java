package com.cafedev.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.common.MessageConst;
import com.cafedev.config.AppConfigurationProperties;
import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.RequestDTO;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.enums.ESortType;
import com.cafedev.model.Comment;
import com.cafedev.model.Feed;
import com.cafedev.repository.CommentRepository;
import com.cafedev.repository.FeedRepository;
import com.cafedev.service.FeedService;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private AppConfigurationProperties config;
	
	@Autowired
	private FeedRepository feedRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public List<Feed> findByOwnerId(Long userId, int offset) {
		RequestDTO<Long> request = new RequestDTO<>(offset, 10, ESortType.DESC, "createDate");
		request.setData(userId);
		List<Feed> feeds = feedRepository.findByOwnerId(request);
		/*Get 3rd comments ***/
		request = new RequestDTO<>(0, config.getMaxResult(), ESortType.ASC, config.getSortValue());
		for (Feed feed : feeds) {
			feed.getComments().clear();
			request.setData(feed.getId());
			List<Comment> comments = commentRepository.findByFeedId(request);
			feed.getComments().addAll(comments);;
		}
		return feeds;
	}
	
	@Override
	public List<Feed> findLatest(int offset) {
		RequestDTO request = new RequestDTO<>(offset, 10, ESortType.DESC, "createDate");
		List<Feed> feeds = feedRepository.findLatest(request);
		request = new RequestDTO<Long>(0, config.getMaxResult(), ESortType.ASC, config.getSortValue());
		for (Feed feed : feeds) {
			feed.getComments().clear();
			request.setData(feed.getId());
			List<Comment> comments = commentRepository.findByFeedId(request);
			feed.getComments().addAll(comments);
		}
		return feeds;
	}
	
	public Feed save(Feed feed) {	
		feed.setCreateDate(new Date());
		return feedRepository.save(feed);
	}
}
