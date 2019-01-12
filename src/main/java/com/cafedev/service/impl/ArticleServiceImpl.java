package com.cafedev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Article;
import com.cafedev.repository.ArticleRepository;
import com.cafedev.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	

	@Override
	public List<Article> findAllByTopicId(RequestDTO<Long> request) {
		
		return articleRepository.findAllByTopicId(request);

	}


	@Override
	public Article getContentById(Long articleId) {
		
		return articleRepository.getContentById(articleId);
		
	}

}
