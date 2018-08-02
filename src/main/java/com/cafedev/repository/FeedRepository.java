package com.cafedev.repository;

import java.util.List;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;

public interface FeedRepository {

	public List<Feed> findByOwnerId(RequestDTO<Long> request);
}
