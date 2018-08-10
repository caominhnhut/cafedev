package com.cafedev.json;

import com.cafedev.dto.Metadata;
import com.cafedev.dto.RequestDTO;
import com.google.gson.Gson;

public class JsonExample {

	public static Gson gson = new Gson();
	
	public static void genReuestDTO(){
		RequestDTO<Long> req = new RequestDTO<Long>();
		req.setData(1L);
		req.setMetadata(new Metadata());
		String json = gson.toJson(req);
		System.out.println(json);
	}	
	/*public static void main(String[] args){
		genReuestDTO();
	}*/
}
