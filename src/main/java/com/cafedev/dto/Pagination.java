package com.cafedev.dto;

public class Pagination {

	private int offset;
	private int maxResult;

	public Pagination(){
		
	}
	
	public Pagination(int offset, int maxResult){
		this.offset = offset;
		this.maxResult = maxResult;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
}
