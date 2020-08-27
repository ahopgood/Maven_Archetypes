package com.alexander.maven.archetypes.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alexander.maven.archetypes.domain.ListPersonDaoImpl;
import com.alexander.maven.archetypes.domain.Person;
import com.alexander.maven.archetypes.domain.PersonDao;

public class PersonControllerTest {

	private PersonController controller;
	private PersonDao personDao;
	
	private String INSURANCE_NO = "JK168376A";
	private String FIRST_NAME	= "Bob";
	private String LAST_NAME	= "Smith";
	private Person person		= new Person(INSURANCE_NO, FIRST_NAME, LAST_NAME);
	
	@Before
	public void setUp() throws Exception {
		controller 		= new PersonController();
		personDao 		= new ListPersonDaoImpl();

		controller.setPersonDao(personDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPerson() {
		personDao.addPerson(person);
		String expected	= "Person :"+person.getLastName()+", "+person.getFirstName()+" #"+person.getNationalInsuranceNumber();
		String actual 	= controller.getPerson(INSURANCE_NO);
		assertEquals("We should have a person's information returned in a string!", expected, actual);
	}
	
	@Test
	public void testGetPerson_CannotBeFound() {
		String expected	= PersonController.PERSON_NOT_FOUND;
		String actual 	= controller.getPerson(INSURANCE_NO);
		assertEquals("We should not be able to find a person's information!", expected, actual);
	}

	@Test
	public void testGetPerson_Null() {
		String expected	= PersonController.PERSON_NOT_FOUND;
		String actual 	= controller.getPerson(null);
		assertEquals("We should not be able to find a person's information!", expected, actual);
	}
}
