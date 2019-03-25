package com.cafedev.enums;

public enum ENotifyStatus {
	READ(1), UNREAD(0);

	private final int notifyStatus;

	public int getNotifyStatus() {
		return notifyStatus;
	}

	ENotifyStatus(int notifyStatus) {
		this.notifyStatus = notifyStatus;
	}
}
