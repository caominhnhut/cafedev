package com.cafedev.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;
import com.cafedev.repository.AbstractJpaRepository;
import com.cafedev.repository.FeedRepository;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Repository
public class FeedRepositoryImpl extends AbstractJpaRepository<Feed> implements
		FeedRepository {

	@Autowired
	private EntityManager em;

	@Override
	public List<Feed> findByOwnerId(RequestDTO request, Long userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Feed> cq = cb.createQuery(Feed.class);
		Root<Feed> root = cq.from(Feed.class);
		cq.select(root);
		cq.where(cb.equal(root.get("user").get("id"), userId));

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

	@Override
	public List<Feed> findLatest(RequestDTO request) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Feed> cq = cb.createQuery(Feed.class);
		Root<Feed> root = cq.from(Feed.class);
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
