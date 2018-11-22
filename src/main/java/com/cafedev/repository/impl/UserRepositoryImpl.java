package com.cafedev.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cafedev.model.User;
import com.cafedev.repository.UserRepository;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	EntityManager em;
	
	@Override
	public Long save(User user) {
		em.persist(user);
		return user.getId();
	}

	@Override
	public User findByUsername(String userName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.equal(root.get("username"), userName));
		Query query = em.createQuery(cq);
		return (User) query.getSingleResult();
	}

}
