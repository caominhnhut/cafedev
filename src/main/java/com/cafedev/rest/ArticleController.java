package com.cafedev.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafedev.dto.ArticleDTO;
import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Article;
import com.cafedev.service.ArticleService;

@Controller
@RequestMapping(value="/rest/article/")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(method = RequestMethod.POST, value="find-all-by-topic-id")
	public ResponseEntity<List<ArticleDTO>> findAllByTopicId(@RequestBody RequestDTO<Long> requestDTO){
		
		List<Article> articles = articleService.findAllByTopicId(requestDTO);
		if(articles==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		List<ArticleDTO> articleDTOs = new ArrayList<ArticleDTO>();		
		for(Article article:articles) {
			ArticleDTO articleDTO = new ArticleDTO();
			articleDTO.coppyArticle(article);
			articleDTOs.add(articleDTO);
		}
		return new ResponseEntity<List<ArticleDTO>>(articleDTOs, HttpStatus.OK);
	}
}
