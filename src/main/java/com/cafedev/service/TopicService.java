package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.RequestDTO;
import com.cafedev.dto.TopicDTO;

public interface TopicService {

	public List<TopicDTO> findAll();
	
	public List<TopicDTO> findById(RequestDTO<Long> request); 
}
