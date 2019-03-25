package com.cafedev.dto;

import com.cafedev.enums.ENotifyType;
import com.cafedev.model.Notify;
import com.cafedev.model.User;

public class NotifyDTO {

	private Long id;
	private String content;
	private ENotifyType type;
	private Long receiverId;

	public Notify toNotify() {
		User receiver = new User();
		Notify notify = new Notify();
		notify.setContent(content);
		notify.setType(type);
		receiver.setId(receiverId);
		notify.setReceiver(receiver);
		return notify;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ENotifyType getType() {
		return type;
	}

	public void setType(ENotifyType type) {
		this.type = type;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
