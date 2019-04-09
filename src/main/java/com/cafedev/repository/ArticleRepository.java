package com.cafedev.repository;

import java.util.List;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Article;

public interface ArticleRepository{

	public List<Article> findByTopicId(Long topicId);
	
	public List<Article> findAllByTopicId(RequestDTO<Long> request);
	
	public Article getContentById(Long articleId);
	
	public List<Article> findAllByTopic(Long topicId);
	
}
