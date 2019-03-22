package com.cafedev.dto;

import java.util.ArrayList;
import java.util.List;

public class ArticlesDTO {
	private String topicName;
	
	private String authorName;
	
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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
