package com.cafedev.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.CommentDTO;
import com.cafedev.dto.CommentRequestDTO;
import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Comment;
import com.cafedev.service.CommentService;

@RestController
@RequestMapping(value = "/rest/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.POST, value = "no-auth/comment/save-data")
	public ResponseEntity<ResponseDTO<CommentDTO>> saveNewComment(@RequestBody CommentRequestDTO commentRequestDto) {
		ResponseDTO<CommentDTO> response = new ResponseDTO<CommentDTO>();
		Comment comment = commentRequestDto.toComment();
		ResponseDTO<Comment> commentResult = this.commentService.saveComment(comment);
		if (commentResult.getData() != null) {
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.copyFrom(commentResult.getData());
			response.setData(commentDTO);
		}else{
			response.setErrorMessage(commentResult.getErrorMessage());
		}
		return new ResponseEntity<ResponseDTO<CommentDTO>>(response,HttpStatus.OK);
	}

}
