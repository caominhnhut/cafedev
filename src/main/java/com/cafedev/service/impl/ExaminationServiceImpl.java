package com.cafedev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafedev.model.Examination;
import com.cafedev.model.ExaminationUser;
import com.cafedev.repository.ExaminationRepository;
import com.cafedev.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	@Autowired
	private ExaminationRepository examination;

	@Override
	public List<Examination> findByUserId(Long id) {
		return examination.findByUserId(id);
	}

	@Override
	public ExaminationUser findByUserAndExam(Long userId,Long examId) {
		return examination.findByUserAndExamId(userId, examId);
	}
	
}
