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
import com.cafedev.repository.UserRepository;
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

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<FeedDTO> findByOwnerId(RequestDTO<Long> request) {
		List<FeedDTO> feedDTOs = new ArrayList<FeedDTO>();
		List<Feed> feeds = feedRepository.findByOwnerId(request);
		/* Get 3rd comments ** */
		RequestDTO<Long> requestDto = new RequestDTO<Long>();
		requestDto.createMetadata(config.getMaxResult(), ESortType.ASC,
				config.getSortValue());
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
		requestDto.createMetadata(config.getMaxResult(), ESortType.ASC,
				config.getSortValue());
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
	public List<Feed> findToDay() {
		return feedRepository.findToDay();
	}

	public ResponseDTO<Feed> save(Feed feed) {
		ResponseDTO<Feed> response = new ResponseDTO<Feed>();
		boolean isValid = checkValidate(feed, response);
		if (isValid) {
			feed.setCreateDate(new Date());
			Feed feedResult = feedRepository.save(feed);
			response.setData(feedResult);
		}
		return response;
	}

	private boolean checkValidate(Feed feed, ResponseDTO<Feed> response) {
		if (feed.getDescription().isEmpty()) {
			response.setErrorMessage(MessageConst.ERROR_DESCRIPTION_EMPTY);
			return false;
		} else if (feed.getFilePath().isEmpty()) {
			response.setErrorMessage(MessageConst.ERROR_FILEPATH_EMPTY);
			return false;
		}
		return true;
	}

}
