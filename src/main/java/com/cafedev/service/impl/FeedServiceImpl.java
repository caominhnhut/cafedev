package com.cafedev.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.config.AppConfigurationProperties;
import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.Metadata;
import com.cafedev.dto.Pagination;
import com.cafedev.dto.RequestDTO;
import com.cafedev.enums.ESortType;
import com.cafedev.model.Comment;
import com.cafedev.model.Feed;
import com.cafedev.repository.CommentRepository;
import com.cafedev.repository.FeedRepository;
import com.cafedev.service.FeedService;

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
		for (Feed feed : feeds) {
			feed.getComments().clear();
			List<Comment> comments = commentRepository.findByFeedId(createRequest(feed.getId()));
			feed.getComments().addAll(comments);
			FeedDTO feedDTO = new FeedDTO();
			feedDTO.copyFrom(feed);
			feedDTOs.add(feedDTO);
		}
		return feedDTOs;
	}
	
	@Override
	public List<FeedDTO> findLatest(RequestDTO<Long> request) {
		List<FeedDTO> feedDTOs = new ArrayList<FeedDTO>();
		List<Feed> feeds = feedRepository.findLatest(request);
		for (Feed feed : feeds) {
			feed.getComments().clear();
			List<Comment> comments = commentRepository.findByFeedId(createRequest(feed.getId()));
			feed.getComments().addAll(comments);
			FeedDTO feedDTO = new FeedDTO();
			feedDTO.copyFrom(feed);
			feedDTOs.add(feedDTO);
		}
		return feedDTOs;
	}
	
	private RequestDTO<Long> createRequest(Long id){
		RequestDTO<Long> requestComment = new RequestDTO<Long>();		
		Metadata metadata = new Metadata();
		Pagination pagination = new Pagination(0, config.getMaxResult());
		metadata.setPagination(pagination);
		metadata.setSortType(ESortType.ASC);
		metadata.setSortValue(config.getSortValue());
		requestComment.setData(id);
		requestComment.setMetadata(metadata);
		return requestComment;
	}
}
