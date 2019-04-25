package com.cafedev.repository.impl;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cafedev.model.ExaminationUser;
import com.cafedev.model.Role;
import com.cafedev.model.User;
import com.cafedev.repository.UserRepository;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	EntityManager em;

	@Override
	public User save(User user) {
			em.persist(user);
			return user;
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

	@Override
	public List<Role> getRoles() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Role> cq = cb.createQuery(Role.class);
		Root<Role> root = cq.from(Role.class);
		cq.select(root);
		Query query = em.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public List<User> findUserById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.equal(root.get("id"), id));
		List<User> user = em.createQuery(cq).getResultList();
		
		return user;
	}
	
	@Override
	public String findAvatar(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.equal(root.get("id"), id));
		User user = em.createQuery(cq).getSingleResult();

		return user.getAvatar();
	}

	@Override
	public User update(User user) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<User> u = cb.createCriteriaUpdate(User.class);
		Root<User> root = u.from(User.class);
		if (user.getAvatar() != null) {
			u.set(root.get("avatar"), user.getAvatar()).where(
					cb.equal(root.get("id"), user.getId()));
		}
		if (user.getEmail() != null) {
			u.set(root.get("email"), user.getEmail()).where(
					cb.equal(root.get("id"), user.getId()));
		}
		if (user.getFirstName() != null) {
			u.set(root.get("firstName"), user.getFirstName()).where(
					cb.equal(root.get("id"), user.getId()));
		}
		if (user.getLastName() != null) {
			u.set(root.get("lastName"), user.getLastName()).where(
					cb.equal(root.get("id"), user.getId()));
		}
		if (user.getPhoneNumber() != null) {
			u.set(root.get("phoneNumber"), user.getPhoneNumber()).where(
					cb.equal(root.get("id"), user.getId()));
		}

		em.createQuery(u).executeUpdate();
		return user;

	}
	
}
