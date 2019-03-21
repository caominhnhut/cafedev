package com.cafedev.rest;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cafedev.common.MessageConst;
import com.cafedev.dto.FeedDTO;
import com.cafedev.dto.RequestDTO;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Feed;
import com.cafedev.service.FeedService;
import com.cafedev.service.FileStorageService;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@RestController
@RequestMapping(value = "/rest/", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedController {

	@Autowired
	private FeedService feedService;

	@Autowired
	private FileStorageService fileStorageService;

	@RequestMapping(method = RequestMethod.POST, value = "feed/find-by-owner")
	public ResponseEntity<List<FeedDTO>> findByOwnerId(
			@RequestBody RequestDTO<Long> requestDTO) {
		List<FeedDTO> feedDto = feedService.findByOwnerId(requestDTO);
		if (feedDto == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FeedDTO>>(feedDto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "no-auth/feed/find-latest")
	public ResponseEntity<List<FeedDTO>> findLatest(
			@RequestBody RequestDTO requestDTO) {
		List<FeedDTO> feedDto = feedService.findLatest(requestDTO);
		if (feedDto == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FeedDTO>>(feedDto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "no-auth/feed/find-today")
	public ResponseEntity<List<FeedDTO>> findToDay() {
		List<FeedDTO> feedDTOs = new ArrayList<FeedDTO>();
		List<Feed> feeds = feedService.findToDay();
		for (Feed feed : feeds) {
			FeedDTO feedDTO = new FeedDTO();
			feedDTO.copyFrom(feed);
			feedDTOs.add(feedDTO);
		}
		return new ResponseEntity<List<FeedDTO>>(feedDTOs, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "feed/save")
	public ResponseEntity<ResponseDTO<FeedDTO>> save(
			@RequestParam("file") MultipartFile file,
			@RequestParam("description") String description) {
		ResponseDTO<FeedDTO> response = new ResponseDTO<FeedDTO>();
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder
				.fromCurrentContextPath().path(MessageConst.FILE_DOWNLOAD)
				.path(fileName).toUriString();
		Feed feed = new Feed();
		feed.setFilePath(fileDownloadUri);
		feed.setDescription(description);
		ResponseDTO<Feed> feedResult = this.feedService.save(feed);
		if (feedResult.getData() != null) {
			FeedDTO feedDto = new FeedDTO();
			feedDto.copyFrom(feedResult.getData());
			response.setData(feedDto);

		} else {
			response.setErrorMessage(feedResult.getErrorMessage());
		}
		return new ResponseEntity<ResponseDTO<FeedDTO>>(response, HttpStatus.OK);
	}
}
