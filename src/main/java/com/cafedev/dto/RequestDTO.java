package com.cafedev.dto;

import com.cafedev.enums.ESortType;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
public class RequestDTO {

	private Metadata metadata;
	private static RequestDTO instance = new RequestDTO();
	
	private RequestDTO(){};

	public static RequestDTO getInstance(){
		return instance; 
	}
	
	public void createRequest(int maxNumber, ESortType sortType, String sortValue){
		Metadata metadata = new Metadata();
		Pagination pagination = new Pagination(0, maxNumber);
		metadata.setPagination(pagination);
		metadata.setSortType(sortType);
		metadata.setSortValue(sortValue);
		this.metadata = metadata;
	}
	
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}
