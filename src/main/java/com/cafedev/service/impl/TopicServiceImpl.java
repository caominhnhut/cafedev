package com.cafedev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.dto.TopicDTO;
import com.cafedev.repository.TopicReposiroty;
import com.cafedev.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicReposiroty topicReposiroty;
	
	@Override
	public List<TopicDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
