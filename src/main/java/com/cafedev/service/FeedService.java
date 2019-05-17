package com.cafedev.service;

import java.util.List;

import com.cafedev.model.Feed;


/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public interface FeedService {

	public List<Feed> findByOwnerId(Long userId, int offset);
	public List<Feed> findLatest(int offset);
	public Feed save(Feed feed);
	
}
