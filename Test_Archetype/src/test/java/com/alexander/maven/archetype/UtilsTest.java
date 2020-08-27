package com.alexander.maven.archetype;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test public void 
	testCastBean_given_null_object() {
		String result = Utils.castBean(null, String.class);
		assertEquals(null, result);
	}

	@Test public void 
	testCastBean_given_incompatible_object_and_class() {
		Object s = 1;
		String result = Utils.castBean(s, String.class);
		assertEquals(null, result);
	}

	@Test public void 
	testCastBean_given_matching_object_and_class() {
		Object s = "My Name";
		String result = Utils.castBean(s, String.class);
		assertEquals(s, result);
	}
}
