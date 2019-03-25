package com.cafedev.model;

import java.util.Date;

import javax.persistence.*;

import org.joda.time.DateTime;

import com.cafedev.enums.ENotifyStatus;
import com.cafedev.enums.ENotifyType;

@Entity
@Table(name = "notify")
public class Notify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "content")
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ENotifyStatus status = ENotifyStatus.UNREAD;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private ENotifyType type = ENotifyType.TAGED;

	@Column(name = "create_date")
	private Date createDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver")
	private User receiver;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender")
	private User sender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ENotifyStatus getStatus() {
		return status;
	}

	public void setStatus(ENotifyStatus status) {
		this.status = status;
	}

	public ENotifyType getType() {
		return type;
	}

	public void setType(ENotifyType type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

}
