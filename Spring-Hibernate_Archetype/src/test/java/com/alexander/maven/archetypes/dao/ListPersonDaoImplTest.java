package com.alexander.maven.archetypes.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alexander.maven.archetypes.dao.ListPersonDaoImpl;
import com.alexander.maven.archetypes.domain.Person;

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
		
		assertTrue(personDao.save(person1) > 0);
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
	}

	@Test
	public void testAddPersonDuplicate() {
		assertTrue(personDao.persons.isEmpty());
		Person person1 = new Person(NAT_INS_NUMBER);
		
		assertTrue(personDao.save(person1) > 0);
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
		
		//Same object
		assertTrue(personDao.save(person1) < 0);
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());		
		
		//New object same national insurance number
		Person person2 = new Person(NAT_INS_NUMBER);
		assertTrue(personDao.save(person2) < 0);
		assertTrue(!personDao.persons.isEmpty());
		assertEquals("We should only have one element in the list.",1,personDao.persons.size());
	}
	
	@Test public void 
	testAddPerson_given_null_insurance_number() {
		assertTrue(personDao.persons.isEmpty());
		Person person1 = new Person(null);
		
		assertTrue(personDao.save(person1) < 0);
		assertTrue(personDao.persons.isEmpty());
		assertEquals("We should have no elements in the list.",0,personDao.persons.size());
	}
	
	@Test public void 
	testAddPerson_given_null_person() {
		assertTrue(personDao.persons.isEmpty());
		assertTrue(personDao.save(null) < 0);
		assertTrue(personDao.persons.isEmpty());
		assertEquals("We should have no elements in the list.",0,personDao.persons.size());
	}
	
	@Test public void 
	testFindPersonByNationalInsuranceNumber() {
		//empty list
		assertTrue(personDao.persons.isEmpty());
		//null insurance number
		assertNull(personDao.findPersonByNationalInsuranceNumber(null));
				
		//find person that isn't in collection
		Person differentFound = personDao.findPersonByNationalInsuranceNumber("XXXXX");
		assertNull(differentFound);
		
		//find person that is in collection
		assertTrue(personDao.save(new Person(NAT_INS_NUMBER)) > 0);
		Person found = personDao.findPersonByNationalInsuranceNumber(NAT_INS_NUMBER);
		assertNotNull(found);
	}
	
	@Test public void 
	testDelete(){
		assertTrue(personDao.save(new Person(NAT_INS_NUMBER)) > 0);
		assertEquals(false, personDao.persons.isEmpty());
		personDao.delete(new Person(NAT_INS_NUMBER));
		assertEquals(true, personDao.persons.isEmpty());
	}
	
	@Test public void 
	testDelete_given_null(){
		assertTrue(personDao.save(new Person(NAT_INS_NUMBER)) > 0);
		assertEquals(false, personDao.persons.isEmpty());
		personDao.delete(null);
		assertEquals(false, personDao.persons.isEmpty());
	}
	
	@Test public void 
	testDelete_given_nullPerson(){
		assertTrue(personDao.save(new Person(NAT_INS_NUMBER)) > 0);
		assertEquals(false, personDao.persons.isEmpty());
		personDao.delete(new Person(null));
		assertEquals(false, personDao.persons.isEmpty());
	}
	
	@Test public void 
	testDelete_given_PersonNotInCollection(){
		assertTrue(personDao.save(new Person(NAT_INS_NUMBER)) > 0);
		assertEquals(false, personDao.persons.isEmpty());
		personDao.delete(new Person("random_text"));
		assertEquals(false, personDao.persons.isEmpty());
	}
}








