package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaDao<E>{
	protected EntityManager entityManager;
	
	public JpaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public E create(E e) {
		this.entityManager.getTransaction().begin();
		
		this.entityManager.persist(e);
		
		this.entityManager.flush();
		this.entityManager.refresh(e);
		
		this.entityManager.getTransaction().commit();
	
		return e;
	}
	
	public E update(E e) {
		this.entityManager.getTransaction().begin();
		
		e = this.entityManager.merge(e);
		
		this.entityManager.getTransaction().commit();
		
		return e;
		
	}
	
	public E find(Class<E> type, Object id) {
		E entity = this.entityManager.find(type, id);
		if(entity != null) {
			this.entityManager.refresh(entity);
		}
		
		return entity;	
	}
	
	public void delete(Class<E> type, Object id) {
		this.entityManager.getTransaction().begin();
		
		Object refrence = this.entityManager.getReference(type, id);
		this.entityManager.remove(refrence);
		
		this.entityManager.getTransaction().commit();
	}
	
	public List<E> findWithNamedQuery(String namedQuery){
		Query query = this.entityManager.createNamedQuery(namedQuery);
		List<E> entities = query.getResultList();
		
		return entities;
	}
	
	
	
	public E findByEmailQuery(String email) {
		Query query = this.entityManager.createNamedQuery("Users.findByEmail");
		
		query.setParameter("email", email);
		
		List<E> entities = query.getResultList();
		System.out.println("entities email size  = " + entities.size() + "--------");
		if(entities != null && entities.size() == 1) {
			return entities.get(0);
		}
		return null;	
	}
	
	
	public E findWithNamedQueryAndParam(String namedQuery, String param, Object paramValue){
		Query query = this.entityManager.createNamedQuery(namedQuery);
		query.setParameter(param, paramValue);
		
		List<E> entities = query.getResultList();
		
		if(entities != null && entities.size() == 1) {
			return entities.get(0);
		}
		return null;
	}
	public List<E> findWithNamedQueryAndParamList(String namedQuery, String param, Object paramValue){
		Query query = this.entityManager.createNamedQuery(namedQuery);
		query.setParameter(param, paramValue);
		
		List<E> entities = query.getResultList();
		System.out.println("from JpaDao -> " + entities.size());
		if(entities != null && entities.size() > 0) {
			return entities;
		}
		return null;
	}
	public E findWithNamedQueryAndParam(String namedQuery, Map<String, Object> params){
		Query query = this.entityManager.createNamedQuery(namedQuery);
		Set<Entry<String, Object>> paramEntries = params.entrySet();
		
		for(Entry<String, Object> entry: paramEntries) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<E> entities = query.getResultList();
		
		if(entities != null && entities.size() == 1) {
			return entities.get(0);
		}
		return null;
	}
	
	
	public long countAll(String namedQuery) {
		Query query = this.entityManager.createNamedQuery(namedQuery);
		long entities = (long) query.getSingleResult();
		
		return entities;
	}
}
