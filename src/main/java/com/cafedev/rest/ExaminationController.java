package com.cafedev.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.dto.ExaminationDTO;
import com.cafedev.model.ExaminationUser;
import com.cafedev.service.ExaminationService;

@RestController
@RequestMapping(value = "/rest/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExaminationController {

	@Autowired
	private ExaminationService examinationService;

	@RequestMapping(method = RequestMethod.GET, value = "examination/find-by-user")
	public List<ExaminationDTO> findByOwnerId(@PathParam("idUser") Long idUser) {
		List<ExaminationDTO> lstExaminationDTO = examinationService
				.findByUserId(idUser);
		return lstExaminationDTO;

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "examination/find-by-user-exam")
	public ExaminationUser findByUserAndExam(@PathParam("idUser") Long userId,@PathParam("idExam") Long examId) {
		ExaminationUser ExaminationDetail = examinationService.findByUserAndExam(userId, examId);
		return ExaminationDetail;
	}
}
