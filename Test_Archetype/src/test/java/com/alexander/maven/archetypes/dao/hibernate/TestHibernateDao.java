package com.alexander.maven.archetypes.dao.hibernate;

import com.alexander.maven.archetypes.domain.TestEntity;

public class TestHibernateDao<T> extends AbstractHibernateDao<TestEntity> {

	public TestHibernateDao() {
		super(TestEntity.class);
	}
}
