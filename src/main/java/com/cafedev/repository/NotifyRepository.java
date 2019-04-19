package com.cafedev.repository;

import java.util.List;

import com.cafedev.model.Notify;

public interface NotifyRepository {

	public List<Notify> findByUserId(Long userId);

	public Notify add(Notify notify);

	public int count(Long userId);

	public String getTaged(Notify notify);

	public String getContent(Notify notify);

	public int getSender(Notify notify);
}
