package com.cafedev.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafedev.config.AppConfigurationProperties;
import com.cafedev.dto.Metadata;
import com.cafedev.dto.Pagination;
import com.cafedev.dto.RequestDTO;
import com.cafedev.enums.ESortType;
import com.cafedev.model.Article;
import com.cafedev.repository.AbstractJpaRepository;
import com.cafedev.repository.ArticleRepository;

@Repository
public class ArticleRepositoryImpl extends AbstractJpaRepository<Article> implements ArticleRepository{

	@Autowired
	EntityManager em;
	
	@Autowired
	AppConfigurationProperties config;
	
	@Override
	public List<Article> findByTopicId(Long topicId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Article> cq = cb.createQuery(Article.class);
		Root<Article> root = cq.from(Article.class);
		cq.where(cb.equal(root.get("topic").get("id"), topicId));
		
		RequestDTO request = RequestDTO.getInstance();
		request.createRequest(config.getMaxTopicNumber(), ESortType.DESC, config.getSortValue());
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
}
