package com.alexander.maven.archetypes.domain.graph.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.tooling.GlobalGraphOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.maven.archetypes.domain.graph.PersonNode;
import com.alexander.maven.archetypes.domain.graph.RootEntity;

@Service
public class PersonService {

	@Autowired private GraphDatabaseService service;
	@Autowired private Neo4jTemplate template;

	@Transactional
	public List<PersonNode> setupNodes(){
		System.out.println("Setting up the Person Nodes");
		System.out.println(service);
		System.out.println("Found the graph database service");
		System.out.println(template);
		PersonNode alex 	= new PersonNode("JK168376A", "Alex","Hopgood");
		PersonNode chris 	= new PersonNode("JK168377B", "Chris","Hopgood");
		template.save(alex);
		template.save(chris);

        final Node referenceNode = template.getReferenceNode();
        template.postEntityCreation(referenceNode,PersonNode.class);
        final PersonNode root = template.findOne(referenceNode.getId(), PersonNode.class);
        root.setFirstName("RootName");
        template.save(root);
        root.related(alex);
        root.related(chris);
        template.save(root);
        alex.related(chris);
        chris.related(alex);
        
        template.save(alex);
        template.save(chris);
		
		return new LinkedList<PersonNode>();
	}
	
	public StringBuilder printOffNodes(){
		GlobalGraphOperations global =  GlobalGraphOperations.at(service);
		
		Iterator<Node> nodeIter = global.getAllNodes().iterator();
		int count = 0;
		StringBuilder builder 			= new StringBuilder();
		while(nodeIter.hasNext()){
			count++;
			Node node 						= nodeIter.next();
			builder.append("Count "+count+" Node Id "+node.getId()+" ");//+" firstName "+node.getProperty("firstName"));
			Iterator<String> properties  	= node.getPropertyKeys().iterator();
			builder.append("Properties ");
			while (properties.hasNext()){
				String property = properties.next();
				builder.append("Property "+property+" value "+node.getProperty(property)+" ");
			}
			builder.append("\n");
		}
		return builder;
	}
	
	
}
