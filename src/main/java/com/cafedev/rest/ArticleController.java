package com.cafedev.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.ArticleDTO;
import com.cafedev.dto.ArticlesDTO;
import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Article;
import com.cafedev.service.ArticleService;

@RestController
@RequestMapping(value="/rest/no-auth/article/")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(method = RequestMethod.POST, value="find-all-by-topic-id")
	public ResponseEntity<ArticlesDTO> findAllByTopicId(@RequestBody RequestDTO<Long> requestDTO){
		
		List<Article> articles = articleService.findAllByTopicId(requestDTO);
		if(articles.size() == 0) {
			return new ResponseEntity<ArticlesDTO>(HttpStatus.NO_CONTENT);
		}
		
		ArticlesDTO articlesDTO = new ArticlesDTO();
		articlesDTO.setTopicName(articles.get(0).getTopic().getName());
		
		List<ArticleDTO> articleList = new ArrayList<ArticleDTO>();
		
		for(Article article:articles) {
			ArticleDTO articleDTO = new ArticleDTO();
			articleDTO.coppyFrom(article);
			articleList.add(articleDTO);
		}
		
		articlesDTO.setArticles(articleList);
		return new ResponseEntity<ArticlesDTO>(articlesDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "content")
	public ResponseEntity<ArticleDTO> getContentById(@RequestParam("id") Long articleId){
		Article article = articleService.getContentById(articleId);
		if(article == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.coppyFrom(article);
		
		return new ResponseEntity<ArticleDTO>(articleDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="count-list-article" )
	public int getAllByTopic(@RequestParam("id") Long topicId){
		return articleService.findAllByTopic(topicId).size();
	}

	@RequestMapping(method = RequestMethod.GET, value = "search")
	public ResponseEntity<List<ArticleDTO>> searchContent(
			@RequestParam("keyword") String keyword) {
		List<Article> articles = articleService.searchByKeyWord(keyword);
		if (articles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<ArticleDTO> lstArticleDTO = new ArrayList<ArticleDTO>();
		for (Article article : articles) {
			ArticleDTO articleDTO = new ArticleDTO();
			articleDTO.coppyFrom(article);
			lstArticleDTO.add(articleDTO);
		}
		return new ResponseEntity<List<ArticleDTO>>(lstArticleDTO,
				HttpStatus.OK);
	}

}
