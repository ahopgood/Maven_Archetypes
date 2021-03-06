package com.alexander.maven.archetypes.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEquals() {
		Person person1 = new Person("JK168376A");
		Person person2 = new Person("JK168376A");
		assertTrue(person1.equals(person2));
		
		assertTrue(person1.equals(person1));
	}
	
	@Test
	public void testNotEquals() {
		Person person1 = new Person("JK168376A");
		Person person2 = new Person("AH123456B");
		assertFalse(person1.equals(person2));
	}
	
	@Test
	public void testHashCodeEquals(){
		Person person1 = new Person("JK168376A");
		Person person2 = new Person("JK168376A");
		assertTrue(person1.equals(person2));
		
		assertEquals(person1.hashCode(), person2.hashCode());
	}
	
	@Test
	public void testHashCodeNotEquals(){
		Person person1 = new Person("JK168376A");
		Person person2 = new Person("AH123456B");
		assertFalse(person1.equals(person2));
		
		assertTrue(person1.hashCode() != person2.hashCode());
	}

}
