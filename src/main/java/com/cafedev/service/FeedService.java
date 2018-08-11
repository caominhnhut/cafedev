package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;

public interface FeedService {

	public List<FeedDTO> findByOwnerId(RequestDTO<Long> request);
	public List<FeedDTO> findLatest(RequestDTO<Long> request);
}
