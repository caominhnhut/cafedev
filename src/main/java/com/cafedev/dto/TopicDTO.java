package com.cafedev.dto;

import java.util.ArrayList;
import java.util.List;

import com.cafedev.model.Article;
import com.cafedev.model.Topic;

public class TopicDTO {

	private String topicName;
	private List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
	
	public void coppyFrom(Topic topic){
		this.topicName = topic.getName();
		for(Article article:topic.getArticles()){
			ArticleDTO articleDto = new ArticleDTO(article.getId(), article.getName(), article.getDescription(), article.getContent(), article.getCreateDate().toString());
			articles.add(articleDto);
		}
	}
	
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
