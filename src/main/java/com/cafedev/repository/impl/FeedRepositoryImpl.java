package com.cafedev.repository.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Feed;
import com.cafedev.repository.FeedRepository;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Repository
public class FeedRepositoryImpl implements FeedRepository {

	@Autowired
	private EntityManager em;

	@Override
	public List<Feed> findByOwnerId(RequestDTO<Long> request) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Feed> cq = cb.createQuery(Feed.class);
		Root<Feed> root = cq.from(Feed.class);
		cq.select(root);
		cq.where(cb.equal(root.get("user").get("id"), request.getData()));

		if (request.getMetadata().getSortType() != null) {
			switch (request.getMetadata().getSortType()) {
			case ASC:
				cq.orderBy(cb.asc(root
						.get(request.getMetadata().getSortValue())));
				break;
			case DESC:
				cq.orderBy(cb.desc(root.get(request.getMetadata()
						.getSortValue())));
				break;
			default:
				break;
			}
		}

		Query query = em.createQuery(cq);
		if (request.getMetadata().getPagination() != null) {
			query.setFirstResult(request.getMetadata().getPagination()
					.getOffset());
			query.setMaxResults(request.getMetadata().getPagination()
					.getMaxResult());
		}

		return query.getResultList();
	}

	@Override
	public List<Feed> findLatest(RequestDTO<Object> request) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Feed> cq = cb.createQuery(Feed.class);
		Root<Feed> root = cq.from(Feed.class);
		if (request.getMetadata().getSortType() != null) {
			switch (request.getMetadata().getSortType()) {
			case ASC:
				cq.orderBy(cb.asc(root
						.get(request.getMetadata().getSortValue())));
				break;
			case DESC:
				cq.orderBy(cb.desc(root.get(request.getMetadata()
						.getSortValue())));
				break;
			default:
				break;
			}
		}

		Query query = em.createQuery(cq);
		if (request.getMetadata().getPagination() != null) {
			query.setFirstResult(request.getMetadata().getPagination()
					.getOffset());
			query.setMaxResults(request.getMetadata().getPagination()
					.getMaxResult());
		}

		return query.getResultList();
	}

	@Override
	public List<Feed> findFeedById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Feed> cq = cb.createQuery(Feed.class);
		Root<Feed> root = cq.from(Feed.class);
		cq.select(root);
		cq.where(cb.equal(root.get("id"), id));
		List<Feed> lstfeed = em.createQuery(cq).getResultList();

		return lstfeed;
	}

	@Override
	public List<Feed> findToDay() {
		// Query query = em.createQuery("SELECT f from Feed f where f.createDate
		// BETWEEN :timestampStart AND CURRENT_TIMESTAMP");
		// query.setParameter("timestampStart",
		// Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.of(0,
		// 0, 0))));
		// List res = query.getResultList();

		Timestamp startDate = Timestamp.valueOf(LocalDateTime.of(
				LocalDate.now(), LocalTime.of(0, 0, 0)));
		Timestamp endDate = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(startDate);
		System.out.println(endDate);

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Feed> cq = cb.createQuery(Feed.class);
		Root<Feed> root = cq.from(Feed.class);
		Path<Date> date = root.<Date> get("createDate");
		cq.select(root);
		cq.where(cb.between(date, startDate, endDate));
		return em.createQuery(cq).getResultList();
	}

	@Override
	@Transactional
	public Feed save(Feed feed) {
		em.persist(feed);
		return feed;
	}
}
