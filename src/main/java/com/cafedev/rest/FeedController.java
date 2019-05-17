package com.cafedev.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cafedev.common.MessageConst;
import com.cafedev.dto.FeedCommentDTO;
import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Feed;
import com.cafedev.model.User;
import com.cafedev.service.FeedCommentService;
import com.cafedev.service.FeedService;
import com.cafedev.service.FileStorageService;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@RestController
@RequestMapping(value="/rest/",produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedController extends RestApiController{

	@Autowired
	private FeedService feedService;
	
	@Autowired
	private FeedCommentService feedCommentService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@RequestMapping(method=RequestMethod.GET, value="feed/find-by-owner")
	public ResponseEntity<List<FeedDTO>> findByOwner(@RequestParam("offset") int offset){
		User user = getAuthenticatedUser();
		List<Feed> feeds = feedService.findByOwnerId(user.getId(), offset);
		List<FeedDTO> feedDtos = new ArrayList<FeedDTO>();
		for (Feed feed : feeds) {
			FeedDTO feedDto = new FeedDTO();
			feedDto.copyFrom(feed);
			feedDtos.add(feedDto);
		}
		return new ResponseEntity<List<FeedDTO>>(feedDtos, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="no-auth/feed/find-latest")
	public ResponseEntity<List<FeedDTO>> findLatest(@RequestParam("offset") int offset){
		List<Feed> feeds = feedService.findLatest(offset);
		List<FeedDTO> feedDtos = new ArrayList<FeedDTO>();
		for (Feed feed : feeds) {
			FeedDTO feedDto = new FeedDTO();
			feedDto.copyFrom(feed);
			feedDtos.add(feedDto);
		}
		return new ResponseEntity<List<FeedDTO>>(feedDtos, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="no-auth/feed/count-by-date")
	@ResponseBody
	public FeedCommentDTO countByDate() {
		return feedCommentService.countByDate();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "feed/create")
	public ResponseEntity<ResponseDTO<FeedDTO>> create(
			@RequestParam(value = "file", required=false) MultipartFile file,
			@RequestParam("description") String description) {
		
		Feed feed = new Feed();
		ResponseDTO<FeedDTO> response = new ResponseDTO<FeedDTO>();
		description = description.trim();
		if(description.isEmpty()){
			response.setErrorMessage(MessageConst.ERROR_DESCRIPTION_EMPTY);
			return new ResponseEntity<ResponseDTO<FeedDTO>>(response, HttpStatus.NOT_ACCEPTABLE);
		}else{
			feed.setDescription(description);
		}
		
		if(file != null){
			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath().path(MessageConst.FILE_DOWNLOAD)
					.path(fileName).toUriString();
			feed.setFilePath(fileDownloadUri);
		}
		User user = getAuthenticatedUser();
		feed.setUser(user);
		
		Feed feedResult = this.feedService.save(feed);
		FeedDTO feedDto = new FeedDTO();
		feedDto.copyFrom(feedResult);
		response.setData(feedDto);
		return new ResponseEntity<ResponseDTO<FeedDTO>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="no-auth/feed/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName){
		Resource resource = fileStorageService.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
}
