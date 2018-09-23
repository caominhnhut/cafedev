package com.cafedev.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafedev.config.AppConfigurationProperties;
import com.cafedev.dto.Metadata;
import com.cafedev.dto.Pagination;
import com.cafedev.dto.RequestDTO;
import com.cafedev.enums.ESortType;
import com.cafedev.model.Topic;
import com.cafedev.repository.AbstractJpaRepository;
import com.cafedev.repository.TopicRepository;

@Repository
public class TopicRepositoryImpl extends AbstractJpaRepository<Topic> implements TopicRepository{

	@Autowired
	EntityManager em;
	
	@Autowired
	AppConfigurationProperties config;
	
	@Override
	public List<Topic> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Topic> cq = cb.createQuery(Topic.class);
		Root<Topic> root = cq.from(Topic.class);
		cq.select(root);
		RequestDTO request = RequestDTO.getInstance();
		request.createRequest(config.getMaxTopicNumber(), ESortType.ASC, config.getSortTopicValue());
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
		if (request.getMetadata().getPagination() != null) {
			query.setFirstResult(request.getMetadata().getPagination().getOffset());
			query.setMaxResults(request.getMetadata().getPagination().getMaxResult());
		}
		
		return query.getResultList();
	}
}
