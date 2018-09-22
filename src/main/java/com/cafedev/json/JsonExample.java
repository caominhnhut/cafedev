package com.cafedev.json;

import com.cafedev.dto.Metadata;
import com.cafedev.dto.RequestDTO;
import com.google.gson.Gson;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */
public class JsonExample {

	public static Gson gson = new Gson();
	
	public static void genReuestDTO(){
		RequestDTO req = RequestDTO.getInstance();
		req.setMetadata(new Metadata());
		String json = gson.toJson(req);
		System.out.println(json);
	}	
//	public static void main(String[] args){
//		genReuestDTO();
//	}
}
