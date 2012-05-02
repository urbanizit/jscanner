package org.urbanizit.jscanner.back.persistence.itf;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.urbanizit.jscanner.back.persistence.bo.EntityItf;

public interface GenericDaoItf<T extends EntityItf<PK>, PK extends Serializable> {


	T save(final T entity);

	List<T> update(final Collection<? extends T> entities);

	T update(final T entity);

	List<T> save(final Collection<? extends T> entities);

	T findById(final PK pk);

	boolean exists(final PK pk);

	List<T> findAll();
	
	Long count();

	void delete(final T entity);
	
	
	void delete(final PK pk);

	void delete(final Collection<? extends T> entities);
	
	void deleteAll();
	
	void flush();
	
	
	T findUniqueByNamedQuery(String sNamedQuery, Map<String, ?> params);
	
	T findUniqueByNamedQuery(String sNamedQuery, String paramName, Object obj);
	

	List<T> findByNamedQuery(String sNamedQuery, Map<String, ?> params);
	
	List<T> findByNamedQuery(String sNamedQuery, String paramName, Object obj);

}