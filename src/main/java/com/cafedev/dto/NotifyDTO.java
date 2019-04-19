package com.cafedev.dto;

import java.util.Date;

import com.cafedev.enums.ENotifyStatus;
import com.cafedev.enums.ENotifyType;
import com.cafedev.model.Notify;
import com.cafedev.model.User;

public class NotifyDTO {

	private Long id;
	private String content;
	private ENotifyType type;
	private ENotifyStatus status;
	private Long receiverId;
	private String senderName;
	private Date create_date;

	public Notify toNotify() {
		User receiver = new User();
		Notify notify = new Notify();
		notify.setContent(content);
		notify.setType(type);
		notify.setStatus(status);
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

	public ENotifyStatus getStatus() {
		return status;
	}

	public void setStatus(ENotifyStatus status) {
		this.status = status;
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

	public String getSenderName() {
		return senderName;
	}

	public void setSenderId(String senderName) {
		this.senderName = senderName;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public void copyFrom(Notify notify) {
		this.id = notify.getId();
		this.content = notify.getContent();
		this.type = notify.getType();
		this.status = notify.getStatus();
		this.senderName = notify.getSender().getFirstName() + " " + notify.getSender().getLastName();
		this.create_date = notify.getCreateDate();
	}

}
