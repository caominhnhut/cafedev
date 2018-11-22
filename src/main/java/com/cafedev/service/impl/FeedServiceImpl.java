package com.cafedev.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.config.AppConfigurationProperties;
import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.RequestDTO;
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
	public List<FeedDTO> findByOwnerId(RequestDTO<Long> request) {
		List<FeedDTO> feedDTOs = new ArrayList<FeedDTO>();
		List<Feed> feeds = feedRepository.findByOwnerId(request);
		/*Get 3rd comments ***/
		RequestDTO<Long> requestDto = new RequestDTO<Long>();
		requestDto.createMetadata(config.getMaxResult(), ESortType.ASC, config.getSortValue());
		for (Feed feed : feeds) {
			feed.getComments().clear();
			requestDto.setData(feed.getId());
			List<Comment> comments = commentRepository.findByFeedId(requestDto);
			feed.getComments().addAll(comments);
			FeedDTO feedDTO = new FeedDTO();
			feedDTO.copyFrom(feed);
			feedDTOs.add(feedDTO);
		}
		return feedDTOs;
	}
	
	@Override
	public List<FeedDTO> findLatest(RequestDTO<Object> request) {
		List<FeedDTO> feedDTOs = new ArrayList<FeedDTO>();
		List<Feed> feeds = feedRepository.findLatest(request);
		RequestDTO<Long> requestDto = new RequestDTO<Long>();
		requestDto.createMetadata(config.getMaxResult(), ESortType.ASC, config.getSortValue());
		for (Feed feed : feeds) {
			feed.getComments().clear();
			requestDto.setData(feed.getId());
			List<Comment> comments = commentRepository.findByFeedId(requestDto);
			feed.getComments().addAll(comments);
			FeedDTO feedDTO = new FeedDTO();
			feedDTO.copyFrom(feed);
			feedDTOs.add(feedDTO);
		}
		return feedDTOs;
	}
}
