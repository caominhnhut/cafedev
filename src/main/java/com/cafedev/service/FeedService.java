package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.RequestDTO;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Feed;


/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface FeedService {

	public List<FeedDTO> findByOwnerId(RequestDTO<Long> request);
	public List<FeedDTO> findLatest(RequestDTO<Object> request);
	public List<Feed> findToDay();
	public ResponseDTO<Feed> save(Feed feed);
	
}
