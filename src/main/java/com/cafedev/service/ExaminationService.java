package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.ExaminationDTO;
import com.cafedev.model.ExaminationUser;

public interface ExaminationService {

	public List<ExaminationDTO> findByUserId(Long id);
	public ExaminationUser findByUserAndExam(Long userId,Long examId);
}
