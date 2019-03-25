package com.cafedev.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cafedev.config.AppConfigurationProperties;
import com.cafedev.dto.RequestDTO;
import com.cafedev.enums.ENotifyStatus;
import com.cafedev.enums.ESortType;
import com.cafedev.model.Notify;
import com.cafedev.repository.NotifyRepository;

@Repository
public class NotifyRepositoryImpl implements NotifyRepository {

	@Autowired
	EntityManager em;

	@Autowired
	AppConfigurationProperties config;

	@Override
	public List<Notify> findByUserId(Long userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Notify> cq = cb.createQuery(Notify.class);
		Root<Notify> root = cq.from(Notify.class);

		Predicate predicateUser = cb.equal(root.get("receiver").get("id"), userId);
		Predicate predicateNotify = cb.equal(root.get("status"), ENotifyStatus.UNREAD);

		cq.where(cb.and(predicateNotify, predicateUser));
		// cq.where(predicateUser);
		RequestDTO<Object> request = new RequestDTO<>();
		request.createMetadata(config.getMaxTopicNumber(), ESortType.DESC, config.getSortValue());
		if (request.getMetadata().getSortType() != null) {
			switch (request.getMetadata().getSortType()) {
			case ASC:
				cq.orderBy(cb.asc(root.get(request.getMetadata().getSortValue())));
				break;
			case DESC:
				cq.orderBy(cb.desc(root.get(request.getMetadata().getSortValue())));
				break;
			default:
				break;
			}
		}
		Query query = em.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public int cont(Long userId) {
		return findByUserId(userId).size();
	}

	/*
	 * public List<Notify> findByUserId1(Long userId) { NotifyRepositoryImpl nri =
	 * new NotifyRepositoryImpl(); List<Notify> listTemp = new ArrayList<Notify>();
	 * for (Notify notify : nri.findByUserId(userId)) { if
	 * (notify.getStatus().toString().equals("UNREAD")) { listTemp.add(notify); } }
	 * return listTemp; }
	 */

	@Override
	@Transactional
	public Notify add(Notify notify) {
		em.persist(notify);
		return notify;
	}

}
