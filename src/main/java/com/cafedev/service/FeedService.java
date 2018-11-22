package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.RequestDTO;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface FeedService {

	public List<FeedDTO> findByOwnerId(RequestDTO<Long> request);
	public List<FeedDTO> findLatest(RequestDTO<Object> request);
}
