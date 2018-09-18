package com.cafedev.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cafedev.model.Topic;
import com.cafedev.repository.AbstractJpaRepository;
import com.cafedev.repository.TopicReposiroty;

@Repository
public class TopicRepositoryImpl extends AbstractJpaRepository<Topic> implements TopicReposiroty{

	@Override
	public List<Topic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
