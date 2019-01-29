package com.cafedev.dto;

public class ResponseDTO<T> {

	private T data;
	private String errorMessage;
	
	public ResponseDTO(){
		
	}
	
	public ResponseDTO(T data, String errorMessage){
		this.data = data;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
