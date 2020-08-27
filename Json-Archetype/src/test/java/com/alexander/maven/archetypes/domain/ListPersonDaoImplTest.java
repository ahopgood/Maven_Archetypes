/**
 * Copyright (c) 2015 Alexander Hopgood
 */
package com.alexander.maven.archetypes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListPersonDaoImplTest {

	private String NAT_INS_NUMBER 				= "JK168376A";
	private String LOWER_CASE_NAT_INS_NUMBER 	= "jk168376a";
	private String MIXED_CASE_NAT_INS_NUMBER	= "jK168376A";
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
		Person person1 = new Person(NAT_INS_NUMBER,"Alex","Hopgood");
		
		assertTrue(personDao.addPerson(person1));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
	}

	@Test
	public void testAddPersonDuplicate() {
		assertTrue(personDao.persons.isEmpty());
		Person person1 = new Person(NAT_INS_NUMBER,"Alex","Hopgood");
		
		assertTrue(personDao.addPerson(person1));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
		
		//Same object
		assertTrue(!personDao.addPerson(person1));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());		
		
		//New object same upper case national insurance number
		Person person2 = new Person(NAT_INS_NUMBER,"Alex","Hopgood");
		assertTrue(!personDao.addPerson(person2));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
		
		//New object same lower case national insurance number
		Person person3 = new Person(LOWER_CASE_NAT_INS_NUMBER,"Alex","Hopgood");
		assertTrue(!personDao.addPerson(person3));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
		
		//New object same mixed case national insurance number
		Person person4 = new Person(MIXED_CASE_NAT_INS_NUMBER,"Alex","Hopgood");
		assertTrue(!personDao.addPerson(person4));
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
	}
	
	@Test
	public void testAddNullPerson() {
		assertTrue(personDao.persons.isEmpty());
		Person person1 = new Person(null,null,null);
		
		assertTrue(! personDao.addPerson(person1));
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
		
		//find person that is in collection with upper case national insurance number
		assertTrue(personDao.addPerson(new Person(NAT_INS_NUMBER,"Alex","Hopgood")));
		Person upperFound = personDao.findPersonByNationalInsuranceNumber(NAT_INS_NUMBER);
		assertNotNull(upperFound);
		
		//find person that is in collection with lower case national insurance number
		Person lowerFound = personDao.findPersonByNationalInsuranceNumber(LOWER_CASE_NAT_INS_NUMBER);
		assertNotNull(lowerFound);
		
		//find person that is in collection with mixed case national insurance number
		Person mixedFound = personDao.findPersonByNationalInsuranceNumber(this.MIXED_CASE_NAT_INS_NUMBER);
		assertNotNull(mixedFound);
	}
}
