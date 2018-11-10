package com.cafedev.jsonfactory;

public class JsonFactory {

	public JsonRequest getJsonRequest(ERequestType requestType){
		JsonRequest request = null;
		switch (requestType) {
		case REQUEST_ID:
			request = new LongRequestDTO();
			break;
		case REQUEST_ARTICLE:
			request = new ArticleRequestDTO();
			break;
		default:
			break;
		}
		return request;
	}
}
