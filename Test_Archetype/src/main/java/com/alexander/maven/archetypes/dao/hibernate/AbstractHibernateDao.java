package com.alexander.maven.archetypes.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.maven.archetypes.dao.BaseDao;

@Repository
@Transactional
public abstract class AbstractHibernateDao<T> implements BaseDao<T> {

	/** Hibernate session factory used for performing hibernate operations */
	private SessionFactory sessionFactory;
	
	private Class<T> entityClass;
	
	protected AbstractHibernateDao(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	/**
	 * Inject the session factory from spring config.
	 * @param sessionFactory
	 */
	@Required
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public T get(long id) {
		return (T)getCurrentSession().get(entityClass, id);
//		throw new UnsupportedOperationException("Get not implemented yet");
//		return null;
	}

	@Override
	public List<T> getAll() {
		throw new UnsupportedOperationException("get All not implemented yet");
//		return null;
	}

	@Override
	public long save(T t) {
		return (Long)getCurrentSession().save(t);
//		throw new UnsupportedOperationException("Save not implemented yet");
//		return false;
	}

	@Override
	public void delete(T t) {
		throw new UnsupportedOperationException("Delete not implemented yet");		
	}

	public Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
}
