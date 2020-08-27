/*
 * (C) Copyright 2013, Masabi Ltd.
 */
package com.alexander.maven.archetypes.domain.dao;

public interface BaseDao<T>{

	public boolean save(T t);
	
	public T get(long id);
}
