package com.cafedev.service;

import java.util.List;

import com.cafedev.dto.ResponseDTO;
import com.cafedev.model.Notify;

public interface NotifyService {

	public List<Notify> findByUserId(Long userId);
	
	public ResponseDTO<Notify> add(Notify notify);
	
	public int count(Long userId);

}
