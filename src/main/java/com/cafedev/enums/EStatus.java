package com.cafedev.enums;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
public enum EStatus {

	ACTIVE(1), PENDING(2), LOCKED(3);

	private final int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	EStatus(int statusCode) {
		this.statusCode = statusCode;
	}
}
