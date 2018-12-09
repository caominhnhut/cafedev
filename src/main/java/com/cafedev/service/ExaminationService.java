package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.ExaminationDTO;

public interface ExaminationService {

	public List<ExaminationDTO> findByUserId(Long id);
}
