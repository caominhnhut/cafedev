package com.cafedev.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.ExaminationDTO;
import com.cafedev.dto.ExaminationDetailDTO;
import com.cafedev.model.ExaminationUser;
import com.cafedev.service.ExaminationService;

@RestController
@RequestMapping(value = "/rest/examination/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExaminationController {

	@Autowired
	private ExaminationService examinationService;

	@RequestMapping(method = RequestMethod.GET, value = "find-by-user")
	public ResponseEntity<List<ExaminationDTO>> findByOwnerId(
			@PathParam("idUser") Long idUser) {
		List<ExaminationDTO> lstExaminationDTO = examinationService
				.findByUserId(idUser);
		if (lstExaminationDTO == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ExaminationDTO>>(lstExaminationDTO,
				HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "find-by-user-exam")
	public ResponseEntity<ExaminationDetailDTO> findByUserAndExam(
			@PathParam("idUser") Long userId, @PathParam("idExam") Long examId) {		
		ExaminationUser examinationUser = examinationService.findByUserAndExam(
				userId, examId);
		ExaminationDetailDTO examinationDetailDTO = new ExaminationDetailDTO();
		if (examinationDetailDTO == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		examinationDetailDTO.copyFrom(examinationUser);
		return new ResponseEntity<ExaminationDetailDTO>(examinationDetailDTO,
				HttpStatus.OK);
	}

}
