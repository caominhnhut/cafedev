package com.cafedev.dto;

public class ArticleDTO {

	private Long id;
	private String name;
	private String description;
	private String content;
	private String createDate;

	public ArticleDTO(Long id, String name, String description, String content, String createDate){
		this.id = id;
		this.name = name;
		this.description = description;
		this.content = content;
		this.createDate = createDate;
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
