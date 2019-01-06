package com.cafedev.dto;

import java.util.ArrayList;
import java.util.List;

public class ArticlesDTO {
	private String topicName;
	
	private List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
	
	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}
	
	
}
