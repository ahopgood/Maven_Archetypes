/**
 * Copyright (c) 2015 Alexander Hopgood
 */
package com.alexander.maven.archetypes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListPersonDaoImplTest {

	private String NAT_INS_NUMBER = "JK168376A";
	private ListPersonDaoImpl personDao;
	
	@Before
	public void setUp() throws Exception {
		personDao = new ListPersonDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddPerson() {
		assertTrue(personDao.persons.isEmpty());
		Person person1 = new Person(NAT_INS_NUMBER);
		
		assertTrue(personDao.addPerson(person1));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
	}

	@Test
	public void testAddPersonDuplicate() {
		assertTrue(personDao.persons.isEmpty());
		Person person1 = new Person(NAT_INS_NUMBER);
		
		assertTrue(personDao.addPerson(person1));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
		
		//Same object
		assertTrue(!personDao.addPerson(person1));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());		
		
		//New object same national insurance number
		Person person2 = new Person(NAT_INS_NUMBER);
		assertTrue(!personDao.addPerson(person2));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
	}
	
	@Test public void 
	testAddPerson_given_null_insurance_number() {
		assertTrue(personDao.persons.isEmpty());
		Person person1 = new Person(null);
		
		assertTrue(! personDao.addPerson(person1));
		assertTrue(personDao.persons.isEmpty());
		assertEquals("We should have no elements in the list.",0,personDao.persons.size());
	}
	
	@Test public void 
	testAddPerson_given_null_person() {
		assertTrue(personDao.persons.isEmpty());
		assertTrue(! personDao.addPerson(null));
		assertTrue(personDao.persons.isEmpty());
		assertEquals("We should have no elements in the list.",0,personDao.persons.size());
	}
	
	@Test
	public void testFindPersonByNationalInsuranceNumber() {
		//empty list
		assertTrue(personDao.persons.isEmpty());
		//null insurance number
		assertNull(personDao.findPersonByNationalInsuranceNumber(null));
				
		//find person that isn't in collection
		Person differentFound = personDao.findPersonByNationalInsuranceNumber("XXXXX");
		assertNull(differentFound);
		
		//find person that is in collection
		assertTrue(personDao.addPerson(new Person(NAT_INS_NUMBER)));
		Person found = personDao.findPersonByNationalInsuranceNumber(NAT_INS_NUMBER);
		assertNotNull(found);
	}
}
