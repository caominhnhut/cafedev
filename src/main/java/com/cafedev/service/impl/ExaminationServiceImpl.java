package com.cafedev.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.dto.ExaminationDTO;
import com.cafedev.model.Examination;
import com.cafedev.model.ExaminationUser;
import com.cafedev.repository.ExaminationRepository;
import com.cafedev.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	@Autowired
	private ExaminationRepository examination;

	@Override
	public List<ExaminationDTO> findByUserId(Long id) {
		List<ExaminationDTO> lstExaminationDTO = new ArrayList<ExaminationDTO>();
		List<Examination> lstExamination = examination.findByUserId(id);
		for (Examination examination : lstExamination) {
			ExaminationDTO examinationDTO = new ExaminationDTO();
			examinationDTO.copyFrom(examination);
			lstExaminationDTO.add(examinationDTO);
		}
		return lstExaminationDTO;
	}

	@Override
	public ExaminationUser findByUserAndExam(Long userId,Long examId) {
		return examination.findByUserAndExamId(userId, examId);
	}
	
}
