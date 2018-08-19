package com.cafedev.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

public abstract class AbstractJpaRepository<T> {

	private Class<T> clazz;
	
	@PersistenceContext
	EntityManager em;
	
	public final void setClazz(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public T findById(Long id){
		return em.find(clazz, id);
	}
	
	public List<T> findAll(){
		return em.createQuery("from "+clazz.getName()).getResultList();
	}
	
	public void create(T entity){
		em.persist(entity);
	}
	
	public T update(T entity){
		return em.merge(entity);
	}
	
	public void delete(T entity){
		em.remove(entity);
	}
	
	public void deleteById(Long id){
		T entity = findById(id);
		delete(entity);
	}
}
