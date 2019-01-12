package com.cafedev.dto;

import com.cafedev.model.Article;

public class ArticleDTO {

	private Long id;
	private String name;
	private String description;
	private String content;
	private String createDate;

	public ArticleDTO() {
		
	}
	
	public ArticleDTO(Long id, String name, String description, String content){
		this.id = id;
		this.name = name;
		this.description = description;
		this.content = content;
	}
	
	public void coppyArticle(Article art) {
		this.id = art.getId();
		this.name = art.getName();
		this.description = art.getDescription();
		this.content = art.getContent();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
