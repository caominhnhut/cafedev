package com.cafedev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;
import com.cafedev.repository.FeedRepository;
import com.cafedev.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepository feedRepository;
	
	@Override
	public List<Feed> findByOwnerId(RequestDTO<Long> request) {
		return feedRepository.findByOwnerId(request);
	}

}
