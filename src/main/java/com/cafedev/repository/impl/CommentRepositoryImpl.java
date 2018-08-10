package com.cafedev.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafedev.dto.RequestDTO;
import com.cafedev.model.Comment;
import com.cafedev.model.Feed;
import com.cafedev.repository.AbstractJpaRepository;
import com.cafedev.repository.CommentRepository;

@Repository
public class CommentRepositoryImpl extends AbstractJpaRepository<Comment> implements CommentRepository {

	@Autowired
	private EntityManager em;
	
	@Override
	public List<Comment> findByFeedId(RequestDTO<Long> request) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
		Root<Comment> comment = cq.from(Comment.class);
		Join<Comment, Feed> join = comment.join("feed", JoinType.LEFT);
		cq.where(cb.equal(join.get("id"), request.getData()));
		
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

}
