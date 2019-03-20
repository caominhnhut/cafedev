package com.cafedev.repository.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List<Feed> findLatest(RequestDTO<Object> request) {
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
	public int countByDate() {
		Timestamp startTime = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.of(00, 00, 00)));
		Timestamp endTime = Timestamp.valueOf(LocalDateTime.now());
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Feed> cq = cb.createQuery(Feed.class);
		Root<Feed> root = cq.from(Feed.class);
		Path<Date> createDate = root.<Date>get("createDate");//cb.between(root.<Date>get("createDate"));
		cq.select(root)
		.where(cb.between(createDate, startTime, endTime));
		Query query = em.createQuery(cq);
		return query.getResultList().size();
		
//		return em.createQuery("select f from Feed f where f.createDate BETWEEN :timestamp AND CURRENT_TIMESTAMP")
//		.setParameter("timestamp", startTime).getResultList().size();
	}
}
