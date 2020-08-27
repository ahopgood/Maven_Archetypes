/*
 * (C) Copyright 2013, Masabi Ltd.
 */
package com.alexander.maven.archetypes.domain.dao;

import java.util.List;

/**
 * A generic Dao that provides template methods for CRUD operations on our entity objects.
 * @author alexhopgood
 *
 * @param <T>
 */
public interface BaseDao<T>{

	public T get(long id);
	public List<T> getAll();
	public boolean save(T t);
	public void delete(T t);
	
}
