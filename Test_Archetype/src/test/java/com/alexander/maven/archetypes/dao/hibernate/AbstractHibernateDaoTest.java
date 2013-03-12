package com.alexander.maven.archetypes.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.maven.archetypes.dao.BaseDao;
import com.alexander.maven.archetypes.domain.TestEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"hibernateContext.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback=true)
public class AbstractHibernateDaoTest {

	private BaseDao<TestEntity> hibernateDao; 
	
	@Required @Autowired
	private void setHibernateDao(BaseDao<TestEntity> hibernateDao){
		this.hibernateDao = hibernateDao;
	}
	
	private final String ALEX	= "alex";
	private final String CHRIS	= "chris";
	private final String NICK	= "nick";
	
	private TestEntity testEntity1 = new TestEntity(ALEX);
	private TestEntity testEntity2 = new TestEntity(CHRIS);
	private TestEntity testEntity3 = new TestEntity(NICK);
	
	@Before
	public void setUp() throws Exception {
		assertNotNull(this.hibernateDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (expected = NullPointerException.class)
	public void testGet_givenNullLong() {
		Long nullLong = null;
		assertEquals(null, this.hibernateDao.get(nullLong));
	}
	
	@Test
	public void testGet_givenNonExistentEntity() {
		assertEquals(null, this.hibernateDao.get(-1));
	}
	
	@Test
	public void testGet() {
		long id = this.hibernateDao.save(testEntity1);
		assertEquals(testEntity1, this.hibernateDao.get(id));
	}
	
	@Test
	public void testSave() {
		long id = this.hibernateDao.save(testEntity1);
		assertEquals(testEntity1, this.hibernateDao.get(id));
	}

	@Test (expected = NullPointerException.class)
	public void testSave_givenNullEntity() {
		this.hibernateDao.save(null);
	}
	
	@Test 
	public void testSave_given() {
		this.hibernateDao.save(null);
	}
}
