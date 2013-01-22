package com.alexander.maven.archetypes.domain.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"personNodes-test-context.xml"})
@Transactional
public class PersonNodeTest {

	@Autowired Neo4jTemplate template;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Transactional public void 
	persistedPerson_ShouldBeRetrievable() {
		PersonNode alex 	= new PersonNode("JK168376A", "Alex", 	"Hopgood");
		PersonNode chris 	= new PersonNode("JK168377B", "Chris", 	"Hopgood");
		template.save(alex);
		template.save(chris);
		
		PersonNode foundPerson = template.findOne(alex.getNodeId(), PersonNode.class);
		assertEquals(alex.getFirstName(), 	foundPerson.getFirstName());
		assertEquals(alex.getLastName(), 	foundPerson.getLastName());
	}


	@Test @Transactional public void 
	persistedPerson_withRelation_ShouldBeRetrievable() {
		PersonNode alex 	= new PersonNode("JK168376A", "Alex", 	"Hopgood");
		PersonNode chris 	= new PersonNode("JK168377B", "Chris", 	"Hopgood");
		alex.related(chris);
				
		template.save(alex);
		template.save(chris);

		PersonNode foundPerson = template.findOne(alex.getNodeId(), PersonNode.class);
		assertEquals(1,foundPerson.getRelatives().size());
		assertEquals(alex.getFirstName(), 	foundPerson.getFirstName());
		assertEquals(alex.getLastName(), 	foundPerson.getLastName());
	}
	
	@Test @Transactional public void 
	persistedPerson_withContribution_ShouldBeRetrievable() {
		PersonNode alex 			= new PersonNode("JK168376A", "Alex", 	"Hopgood");
		NationalInsurance alexNi 	= new NationalInsurance("JK168376A");
		template.save(alexNi);
		PersonNode chris 			= new PersonNode("JK16837BA", "Chris", 	"Hopgood");
		alex.related(chris);

		NationalInsurance foundNi = template.findOne(alexNi.getId(), NationalInsurance .class);
		assertEquals("JK168376A",foundNi.getNationalInsuranceNumber());
		
		alex.contributeNationalInsurance(alexNi, 1000);
		template.save(alex);
		
		PersonNode foundPerson = template.findOne(alex.getNodeId(), PersonNode.class);
		assertEquals(1,foundPerson.getRelatives().size());
		
		assertEquals(1,foundPerson.getContribs().size());
		Iterator<NIContribution> iter 	= foundPerson.getContribs().iterator();
		NIContribution contrib 			= iter.next();
		assertEquals(1000,contrib.getPaymentAmount());
		
		System.out.println(contrib.getNationalInsurance());
		
		assertNotNull(contrib.getNationalInsurance().getId());
		assertEquals("JK168376A",contrib.getNationalInsurance()
				.getNationalInsuranceNumber());		
	}
}
