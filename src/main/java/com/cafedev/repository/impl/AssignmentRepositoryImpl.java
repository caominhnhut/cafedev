package com.cafedev.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
		CriteriaQuery<AssignmentUser> cq = cb.createQuery(AssignmentUser.class);
		Root<AssignmentUser> root = cq.from(AssignmentUser.class);
		cq.select(root);
		cq.where(cb.equal(root.get("user").get("id"), userId));
		Query query = em.createQuery(cq);
		List<Assignment> assignments = new ArrayList<Assignment>();
		List<AssignmentUser> assignmentUsers = query.getResultList();
		for(AssignmentUser assignmentUser: assignmentUsers){
			assignments.add(assignmentUser.getAssignment());
		}
		return assignments;
	}
}
