package com.cafedev.dto;

public class FeedCommentDTO {
	private int numberFeed;
	private int numberComment;
	
	public FeedCommentDTO() {
		
	}
	
	public FeedCommentDTO(int numberFeed, int numberComment) {
		this.numberFeed = numberFeed;
		this.numberComment = numberComment;
	}

	public int getNumberFeed() {
		return numberFeed;
	}

	public void setNumberFeed(int numberFeed) {
		this.numberFeed = numberFeed;
	}

	public int getNumberComment() {
		return numberComment;
	}

	public void setNumberComment(int numberComment) {
		this.numberComment = numberComment;
	}
	
}
