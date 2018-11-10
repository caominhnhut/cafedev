package com.cafedev.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.dto.RequestDTO;
import com.cafedev.dto.TopicDTO;
import com.cafedev.model.Article;
import com.cafedev.model.Topic;
import com.cafedev.repository.ArticleRepository;
import com.cafedev.repository.TopicRepository;
import com.cafedev.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public List<TopicDTO> findAll() {
		List<TopicDTO> topicDtos = new ArrayList<TopicDTO>();
		List<Topic> topics = topicRepository.findAll();
		if(topics != null){
			for(Topic topic:topics){
				List<Article> articles = articleRepository.findByTopicId(topic.getId());
				topic.getArticles().clear();
				topic.getArticles().addAll(articles);
				
				TopicDTO topicDto = new TopicDTO();
				topicDto.coppyFrom(topic);
				topicDtos.add(topicDto);
			}
		}
		return topicDtos;
	}

	@Override
	public List<TopicDTO> findById(RequestDTO<Long> request) {
		return null;
	}
}
