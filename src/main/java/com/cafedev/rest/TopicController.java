package com.cafedev.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.RequestDTO;
import com.cafedev.dto.TopicDTO;
import com.cafedev.service.TopicService;

/**
 * Created by Nhut Nguyen on 28-08-2018.
 */

@RestController
@RequestMapping(value="/rest/",produces = MediaType.APPLICATION_JSON_VALUE)
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@RequestMapping(method=RequestMethod.GET, value="no-auth/topic")
	public ResponseEntity<List<TopicDTO>> findAll(){
		List<TopicDTO> topicDtos = topicService.findAll();
		return new ResponseEntity<List<TopicDTO>>(topicDtos, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="no-auth/topic")
	public ResponseEntity<List<TopicDTO>> findById(@RequestBody RequestDTO<Long> requestDto){
		List<TopicDTO> topicDtos = topicService.findById(requestDto);
		if(topicDtos == null){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TopicDTO>>(topicDtos, HttpStatus.OK);
	}
	

}
