package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;

public interface FeedService {

	public List<Feed> findByOwnerId(RequestDTO<Long> request);
}
