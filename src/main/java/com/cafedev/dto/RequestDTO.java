package com.cafedev.dto;

import com.cafedev.enums.ESortType;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
public class RequestDTO<T> {

	private Metadata metadata;
	private T data;
	
	public RequestDTO(){};
	
	public RequestDTO(int offset, int maxNumber, ESortType sortType, String sortValue){
		Pagination pagination = new Pagination(offset, maxNumber);
		metadata = new Metadata(pagination, sortType, sortValue);
	}
	
	public Metadata getMetadata() {
		return metadata;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
