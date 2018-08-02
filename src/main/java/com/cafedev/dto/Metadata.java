package com.cafedev.dto;

import com.cafedev.enums.ESortType;

public class Metadata {

	private Pagination pagination;
	private ESortType sortType;
	private String sortValue;

	public Metadata(){
		pagination = new Pagination();
		sortType = ESortType.DESC;
		sortValue = "createDate";
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
