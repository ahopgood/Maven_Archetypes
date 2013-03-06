package com.alexander.maven.archetypes.domain;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;

import com.alexander.maven.archetypes.domain.dao.BaseDao;

public abstract class AbstractHibernateDao<T> implements BaseDao<T> {

	/** Hibernate session factory used for performing hibernate operations */
	private SessionFactory sessionFactory;
	
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
		throw new UnsupportedOperationException("Get not implemented yet");
//		return null;
	}

	@Override
	public List<T> getAll() {
		throw new UnsupportedOperationException("get All not implemented yet");
//		return null;
	}

	@Override
	public boolean save(T t) {
		throw new UnsupportedOperationException("Save not implemented yet");
//		return false;
	}

	@Override
	public void delete(T t) {
		throw new UnsupportedOperationException("Delete not implemented yet");		
	}

}
