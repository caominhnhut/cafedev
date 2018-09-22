package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Article;

public interface ArticleRepository{

	public List<Article> findByTopicId(Long topicId);
}
