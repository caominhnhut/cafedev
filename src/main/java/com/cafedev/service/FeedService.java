package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.RequestDTO;

public interface FeedService {

	public List<FeedDTO> findByOwnerId(RequestDTO<Long> request);
}
