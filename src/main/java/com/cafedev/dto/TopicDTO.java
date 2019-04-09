package com.cafedev.dto;

import java.util.ArrayList;
import java.util.List;

import com.cafedev.common.TimeProvider;
import com.cafedev.model.Article;
import com.cafedev.model.Topic;

public class TopicDTO {

	private String topicName;
	private Long topicId;
	private List<ArticleDTO> articles = new ArrayList<ArticleDTO>();

	public void coppyFrom(Topic topic) {
		this.topicName = topic.getName();
		this.topicId = topic.getId();
		List<Article> arts = topic.getArticles();
		if (arts != null) {
			for (Article article : arts) {
				ArticleDTO articleDto = new ArticleDTO();
				articleDto.setId(article.getId());
				articleDto.setName(article.getName());
				articleDto.setDescription(article.getDescription());
				articleDto.setCreateDate(TimeProvider.convertDateToString(article.getCreateDate()));
				articleDto.setAuthorName(article.getUser().getFirstName() + " " + article.getUser().getLastName());
				articles.add(articleDto);
			}
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

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
