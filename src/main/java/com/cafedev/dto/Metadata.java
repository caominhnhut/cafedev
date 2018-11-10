package com.cafedev.dto;

import com.cafedev.enums.ESortType;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
public class Metadata {

	private Pagination pagination = new Pagination();
	private ESortType sortType;
	private String sortValue;

	public Metadata(){
		sortType = ESortType.DESC;
		sortValue = "createDate";
	}
	
	public Metadata(Pagination pagination, ESortType sortType, String sortValue){
		this.pagination = pagination;
		this.sortType = sortType;
		this.sortValue = sortValue;
	}
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ESortType getSortType() {
		return sortType;
	}

	public void setSortType(ESortType sortType) {
		this.sortType = sortType;
	}

	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String sortValue) {
		this.sortValue = sortValue;
	}
}
