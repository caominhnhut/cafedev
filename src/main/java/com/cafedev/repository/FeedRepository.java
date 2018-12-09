package com.cafedev.repository;

import java.util.List;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface FeedRepository {

	public List<Feed> findByOwnerId(RequestDTO<Long> request);
	public List<Feed> findLatest(RequestDTO<Object> request);

}
