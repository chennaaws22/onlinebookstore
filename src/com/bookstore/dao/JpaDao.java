package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDao<E>{
	private static EntityManagerFactory entityManagerFactory;	
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite"); 
	}
	
	public JpaDao() {
		
	}
	
	public E create(E e) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		System.out.println("Transaction begin");
		entityManager.persist(e);
		entityManager.flush();
		entityManager.refresh(e);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();
		return e;
	}
	
	public E update(E e) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		
		e = entityManager.merge(e);
		
		entityManager.getTransaction().commit();
		
		entityManager.close();

		return e;
		
	}
	
	public E find(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		E entity = entityManager.find(type, id);
		if(entity != null) {
			entityManager.refresh(entity);
		}
		
		entityManager.close();
		return entity;	
	}
	
	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		
		Object refrence = entityManager.getReference(type, id);
		entityManager.remove(refrence);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<E> findWithNamedQuery(String namedQuery){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(namedQuery);
		List<E> entities = query.getResultList();
		entityManager.close();
		return entities;
	}
	
	
	public List<E> findWithNamedQueryLimit(String namedQuery, int limit){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(namedQuery);
		query.setFirstResult(0);
		query.setMaxResults(4);
		List<E> entities = query.getResultList();
		
		entityManager.close();
		return entities;
	}
	
	public E findByEmailQuery(String email) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery("Users.findByEmail");
		
		query.setParameter("email", email);
		
		List<E> entities = query.getResultList();
		entityManager.close();
		System.out.println("entities email size  = " + entities.size() + "--------");
		E e = null;
		if(entities != null && entities.size() == 1) {
			e = entities.get(0);
			
		}
		
		return e;	
	}
	
	
	public E findWithNamedQueryAndParam(String namedQuery, String param, Object paramValue){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter(param, paramValue);
		
		List<E> entities = query.getResultList();
		entityManager.close();
		if(entities != null && entities.size() == 1) {
			return entities.get(0);
		}
		return null;
	}
	public List<E> findWithNamedQueryAndParamList(String namedQuery, String param, Object paramValue){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter(param, paramValue);
		
		List<E> entities = query.getResultList();
		entityManager.close();
		System.out.println("from JpaDao -> " + entities.size());
		if(entities != null && entities.size() > 0) {
			return entities;
		}
		return null;
	}
	public E findWithNamedQueryAndParam(String namedQuery, Map<String, Object> params){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(namedQuery);
		Set<Entry<String, Object>> paramEntries = params.entrySet();
		
		for(Entry<String, Object> entry: paramEntries) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<E> entities = query.getResultList();
		entityManager.close();
		if(entities != null && entities.size() == 1) {
			return entities.get(0);
		}
		return null;
	}
	
	
	public long countAll(String namedQuery) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(namedQuery);
		long entities = (long) query.getSingleResult();
		entityManager.close();
		return entities;
	}
	
	
	public long countWithNamedQueryAndParam(String namedQuery, String param, Object paramValue){
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter(param, paramValue);
		long entitiesCount = (long)query.getSingleResult();
		entityManager.close();
		
		 return entitiesCount;
	}
	
	public void close() {
		entityManagerFactory.close();
	}
}
