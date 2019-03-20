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
import com.cafedev.model.Comment;
import com.cafedev.repository.CommentRepository;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@Repository
public class CommentRepositoryImpl implements CommentRepository {

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Comment> findByFeedId(RequestDTO<Long> request) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
		Root<Comment> comment = cq.from(Comment.class);
		cq.select(comment);
		cq.where(cb.equal(comment.get("feed").get("id"), request.getData()));
		
		if (request.getMetadata().getSortType() != null) {
			switch (request.getMetadata().getSortType()) {
			case ASC:
				cq.orderBy(cb.asc(comment.get(request.getMetadata().getSortValue())));
				break;
			case DESC:
				cq.orderBy(cb.desc(comment.get(request.getMetadata().getSortValue())));
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
	@Transactional
	public Comment save(Comment comment) {
		em.persist(comment);
		return comment;
	}


	@Override
	public int countByDate() {
		Timestamp startTime = Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.of(00, 00, 00)));
		Timestamp endTime = Timestamp.valueOf(LocalDateTime.now());
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
		Root<Comment> root = cq.from(Comment.class);
		Path<Date> createDate = root.<Date>get("createDate");
		cq.select(root).where(cb.between(createDate, startTime, endTime));
		Query query = em.createQuery(cq);
		return query.getResultList().size();
	}
	
}
