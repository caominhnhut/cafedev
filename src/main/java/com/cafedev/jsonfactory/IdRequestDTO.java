package com.cafedev.jsonfactory;

import com.cafedev.dto.RequestDTO;
import com.cafedev.enums.ESortType;

public class IdRequestDTO extends JsonRequest{

	
	
	@Override
	public String createJson() {
		RequestDTO<Long> req = new RequestDTO<Long>(0, 10, ESortType.DESC, "createDate");
		req.setData(1L);
		String json = gson.toJson(req);
		log.info(json);
		return json;
	}

}
