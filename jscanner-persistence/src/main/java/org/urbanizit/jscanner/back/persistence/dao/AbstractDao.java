package org.urbanizit.jscanner.back.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.urbanizit.jscanner.back.persistence.bo.EntityItf;
import org.urbanizit.jscanner.back.persistence.itf.GenericDaoItf;


public class AbstractDao<T extends EntityItf<PK>, PK extends Serializable> implements GenericDaoItf<T, PK> {


	private final Logger log = LoggerFactory.getLogger(AbstractDao.class);

	public static final String COUNT_QUERY_STRING = "select count(x) from %s x";
	public static final String READ_ALL_QUERY = "select x from %s x";
	public static final String DELETE_ALL_QUERY_STRING = "delete from %s x";

	protected Class<EntityItf<PK>> persistentClass;

	/**
	 * {@link EntityManager}
	 */
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		Type [] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		
		if (types[0] instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) types[0];
			persistentClass = (Class<EntityItf<PK>>) type.getRawType();
		} else {
			persistentClass = (Class<EntityItf<PK>>) types[0];
		}
	}

	protected Class<EntityItf<PK>> getPersistentClass() {
		return persistentClass;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public T findById(PK pk) {
		log.debug("{} -- findById({})", getPersistentClass().getName(), pk);
		
		return (T) getEntityManager().find(getPersistentClass(), pk);
	}


	public Long count() {
		try {
			log.debug("{} -- count()", getPersistentClass().getName());
			
			return (Long) getEntityManager().createQuery(String.format(COUNT_QUERY_STRING, getPersistentClass().getName())).getSingleResult();
		} catch (NoResultException e) {
			return 0L;
		}
	}


	public void delete(T entity) {
		log.debug("{} delete({})", getPersistentClass().getName(), entity);
		
		getEntityManager().remove(entity);
	}


	public void delete(Collection<? extends T> entities) {
		log.debug("{} delete({})", getPersistentClass().getName(), entities);
		
		for(T entity : entities) {
			this.delete(entity);
		}
	}

	public void deleteAll() {
		log.debug("{}  deleteAll()", getPersistentClass().getName());
		
		getEntityManager().createQuery(String.format(DELETE_ALL_QUERY_STRING, getPersistentClass().getName())).executeUpdate();
	}

	public boolean exists(PK pk) {
		log.debug("{}  exists({})", getPersistentClass().getName(), pk);
		
		return null != this.findById(pk);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		log.debug("{}  findAll()", getPersistentClass().getName());
		
		return (List<T>) getEntityManager().createQuery(String.format(READ_ALL_QUERY, getPersistentClass().getName())).getResultList();
	}

	public T save(T entity) {
		log.debug("{} save({})", getPersistentClass().getName(), entity);
		
		getEntityManager().persist(entity);
		return entity;
	}

	public List<T> save(Collection<? extends T> entities) {
		log.debug("{} save({})", getPersistentClass().getName(), entities);
		
		List<T> result = new ArrayList<T>(entities.size());

		for (T entity : entities) {
			result.add(this.save(entity));
		}

		return result;
	}

	public T update(T entity) {
		log.debug("{} update({})", getPersistentClass().getName(), entity);
		
		return getEntityManager().merge(entity);
	}

	public List<T> update(Collection<? extends T> entities) {
		log.debug("{} update({})", getPersistentClass().getName(), entities);
		
		List<T> result = new ArrayList<T>(entities.size());

		for (T entity : entities) {
			result.add(this.update(entity));
		}

		return result;
	}

	public void flush() {
		log.debug("{} flush()", getPersistentClass().getName());
		
		getEntityManager().flush();
	}
	

	private Query getNamedQuery(String sNamedQuery) {
		log.debug("{} getNamedQuery({})", getPersistentClass().getName(), sNamedQuery);
		
		return getEntityManager().createNamedQuery(sNamedQuery);
	}
	
	private void setParameters(Query query, Map<String, ?> params) {
		log.debug("{}  setParameters({}, {})", new Object[] {getPersistentClass().getName(), query, params});
		
		if (query != null && params != null) {
			for(Entry<String, ?> paramEntry : params.entrySet()) {
				log.debug("\tParameter [{} - {}]", paramEntry.getKey(), paramEntry.getValue());
				query.setParameter(paramEntry.getKey(), paramEntry.getValue());
			}
		}
	}

	public List<T> findByNamedQuery(String sNamedQuery, String paramName, Object obj) {
		log.debug("{} findByNamedQuery({}, {}, {})", new Object[] {getPersistentClass().getName(), sNamedQuery, paramName, obj});
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, obj);
		
		return findByNamedQuery(sNamedQuery, params);
	}
	

	public T findUniqueByNamedQuery(String sNamedQuery, String paramName, Object obj) {
		log.debug("{} findUniqueByNamedQuery({}, {}, {})", new Object[] {getPersistentClass().getName(), sNamedQuery, paramName, obj});
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(paramName, obj);
		
		return findUniqueByNamedQuery(sNamedQuery, params);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String sNamedQuery, Map<String, ?> params) {
		log.debug("{} findByNamedQuery({}, {})", new Object[] {getPersistentClass().getName(), sNamedQuery, params});
		
		Query namedQuery = getNamedQuery(sNamedQuery);
		setParameters(namedQuery, params);
		
		return (List<T>) namedQuery.getResultList();
	}
	

	@SuppressWarnings("unchecked")
	public T findUniqueByNamedQuery(String sNamedQuery, Map<String, ?> params) {
		log.debug("{} findUniqueByNamedQuery({}, {})", new Object[] {getPersistentClass().getName(), sNamedQuery, params});
		
		Query namedQuery = getNamedQuery(sNamedQuery);
		setParameters(namedQuery, params);
		
		return (T) namedQuery.getSingleResult();
	}

	public void delete(PK pk) {
		log.debug("{} delete({})", new Object[] {getPersistentClass().getName(), pk});
		
		this.delete(this.findById(pk));
	}
}