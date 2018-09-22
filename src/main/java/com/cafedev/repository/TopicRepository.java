package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Topic;

public interface TopicRepository {

	public List<Topic> findAll();
}
