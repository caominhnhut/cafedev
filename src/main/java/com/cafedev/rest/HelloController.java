package com.cafedev.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cafedev.model.Authority;
import com.cafedev.model.User;
import com.cafedev.service.UserService;

@RestController
@RequestMapping(value = "/rest/")
public class HelloController {

	@RequestMapping(value = "hello")
	@ResponseBody
	public String hello() {
		return "I am spring boot";
	}

	@Autowired
	private EntityManager em;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "get-data")
	@ResponseBody
	public List<Authority> getAuth(@RequestParam("userId") int userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Authority> cq = cb.createQuery(Authority.class);
		Root<User> root = cq.from(User.class);
		cq.where(cb.equal(root.get("id"), userId));
		Join<User, Authority> join = root.join("authorities");
		cq = cq.select(join);
		TypedQuery<Authority> tq = em.createQuery(cq);
		List<Authority> ls = tq.getResultList();
		return ls;
	}
	
	@RequestMapping(value = "get-data-sql")
	@ResponseBody
	public List<Authority> getDataSql(@RequestParam("userId") int userId) {
		Query q = em.createNativeQuery("select * from user_authority where user_id = "+userId);
		return q.getResultList();
	}

	@RequestMapping(value = "/user/all")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	@RequestMapping(value = "/user/{userId}")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}
	
	@RequestMapping(value = "/cap-nhat", method=RequestMethod.POST)
	@ResponseBody
	public Boolean update(@RequestBody User user) {
		em.unwrap(Session.class).update(user);
		return true;
	}
}
