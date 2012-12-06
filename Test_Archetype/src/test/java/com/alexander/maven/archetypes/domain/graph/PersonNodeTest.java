package com.alexander.maven.archetypes.domain.graph;

import java.util.Collection;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.internal.symbols.RelationshipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.mapping.MappingPolicy;
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
		PersonNode alex 	= new PersonNode("Alex", 	"Hopgood", "JK168376A");
		PersonNode chris 	= new PersonNode("Chris", 	"Hopgood", "JK168376C");
		template.save(alex);
		template.save(chris);
		
		PersonNode foundPerson = template.findOne(alex.getId(), PersonNode.class);
	}


	@Test @Transactional public void 
	persistedPerson_withRelation_ShouldBeRetrievable() {
		PersonNode alex 	= new PersonNode("Alex", 	"Hopgood", "JK168376A");
		PersonNode chris 	= new PersonNode("Chris", 	"Hopgood", "JK168376C");
		alex.related(chris);
		
		Set<PersonNode> relations = alex.getRelatives();
		System.out.println(relations.size());
		for (PersonNode relative : relations){
			System.out.println("Alex is related to "+relative.getFirstName());
		}

				
		template.save(alex);
		template.save(chris);

		PersonNode foundPerson = template.findOne(alex.getId(), PersonNode.class);
		System.out.println("Found "+foundPerson.getFirstName());
		Set<PersonNode> savedRelations = foundPerson.getRelatives();
		System.out.println(savedRelations.size());
		for (PersonNode relative : savedRelations){
			System.out.println(relative.getFirstName());
		}
		
	}
}
