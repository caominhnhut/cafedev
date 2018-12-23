package com.cafedev.jsonfactory;

import com.cafedev.dto.ArticleDTO;
import com.cafedev.dto.RequestDTO;
import com.cafedev.enums.ESortType;

public class ArticleRequestDTO extends JsonRequest{

	@Override
	public String createJson() {
		RequestDTO<ArticleDTO> req = new RequestDTO<ArticleDTO>();
		req.createMetadata(10, ESortType.DESC, "createDate");
		//req.setData(new ArticleDTO(1L, "Article name", "This is article desc", "Hi, how are you", "5-11-2018"));
		String json = gson.toJson(req);
		log.info(json);
		return json;
	}

}
