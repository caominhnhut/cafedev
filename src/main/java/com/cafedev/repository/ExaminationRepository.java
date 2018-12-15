package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Examination;
import com.cafedev.model.ExaminationUser;


public interface ExaminationRepository {

	public List<Examination> findByUserId(Long id);
	
	public ExaminationUser findByUserAndExamId(Long userId,Long examId);
}
