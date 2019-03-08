package com.cafedev.service;

import java.util.List;

import com.cafedev.model.Examination;
import com.cafedev.model.ExaminationUser;

public interface ExaminationService {

	public List<Examination> findByUserId(Long id);
	public ExaminationUser findByUserAndExam(Long userId,Long examId);
}
