package com.cafedev.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafedev.model.Assignment;
import com.cafedev.model.AssignmentUser;
import com.cafedev.repository.AssignmentRepository;

@Repository
public class AssignmentRepositoryImpl implements AssignmentRepository {
	
	@Autowired
	private EntityManager em;

	@Override
	public List<Assignment> findByUserId(Long userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Assignment> cq = cb.createQuery(Assignment.class);
		Root<Assignment> root = cq.from(Assignment.class);
		Join<Assignment, AssignmentUser> join = root.join("assignmentUsers");
		cq.select(root);
		cq.where(cb.equal(join.get("user").get("id"), userId));
		Query query = em.createQuery(cq);
		return query.getResultList();
	}
	
	
}
