package com.cafedev.dto;

import com.cafedev.model.Comment;
import com.cafedev.model.Feed;
import com.cafedev.model.User;


public class CommentRequestDTO {

	private long feedId;
	private long userId;
	private String content;
	
public Comment toComment(){
	Comment comment = new Comment();
	Feed feed = new Feed();
	User user = new User();
	comment.setContent(content);
	feed.setId(feedId);
	user.setId(userId);
	comment.setFeed(feed);
	comment.setUser(user);
	return comment;
}

	public long getFeedId() {
		return feedId;
	}
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
