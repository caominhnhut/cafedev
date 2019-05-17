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

import com.cafedev.model.Examination;
import com.cafedev.model.ExaminationUser;
import com.cafedev.repository.ExaminationRepository;

@Repository
public class ExaminationRepositoryImpl implements ExaminationRepository {
	@Autowired
	private EntityManager em;

	@Override
	public List<Examination> findByUserId(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ExaminationUser> cq = cb.createQuery(ExaminationUser.class);
		Root<ExaminationUser> root = cq.from(ExaminationUser.class);
		cq.select(root);
		cq.where(cb.equal(root.get("user").get("id"), id));
		Query query = em.createQuery(cq);
		List<Examination> examinations = new ArrayList<Examination>();
		List<ExaminationUser> examinationUsers = query.getResultList();
		for(ExaminationUser examinationUser: examinationUsers){
			examinations.add(examinationUser.getExamination());
		}
		return examinations;
	}
	
	@Override
	public ExaminationUser findByUserAndExamId(Long userId,Long examId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ExaminationUser> cq = cb.createQuery(ExaminationUser.class);
		Root<ExaminationUser> root = cq.from(ExaminationUser.class);
		
		Predicate predicateUser = cb.equal(root.get("user").get("id"), userId);
		Predicate predicateExam = cb.equal(root.get("examination").get("id"), examId);
		
		cq.where(cb.and(predicateUser, predicateExam));
		ExaminationUser examinationUser = (ExaminationUser) em.createQuery(cq).getSingleResult();
		return examinationUser;
	}
}
