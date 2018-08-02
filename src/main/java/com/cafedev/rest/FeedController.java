package com.cafedev.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;
import com.cafedev.service.FeedService;

@RestController
@RequestMapping(value="/rest/feed/",produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	@RequestMapping(method=RequestMethod.POST, value="find-by-owner")
	public ResponseEntity<List<Feed>> findByOwnerId(@RequestBody RequestDTO<Long> requestDTO){
		List<Feed> feeds = feedService.findByOwnerId(requestDTO);
		if(feeds == null){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}
	
}
