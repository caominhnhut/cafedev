package com.cafedev.jsonfactory;

import com.cafedev.dto.RequestDTO;
import com.cafedev.enums.ESortType;

public class LongRequestDTO extends JsonRequest{

	@Override
	public String createJson() {
		RequestDTO req = new RequestDTO<Long>();
		req.createMetadata(10, ESortType.DESC, "createDate");
		req.setData(1L);
		String json = gson.toJson(req);
		System.out.println(json);
		return json;
	}

}
