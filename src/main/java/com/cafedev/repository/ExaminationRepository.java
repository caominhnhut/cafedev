package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Examination;


public interface ExaminationRepository {

	public List<Examination> findByUserId(Long id);
}
