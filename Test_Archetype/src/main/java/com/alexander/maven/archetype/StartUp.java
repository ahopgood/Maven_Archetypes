package com.alexander.maven.archetype;

import java.util.Iterator;

import org.neo4j.cypher.internal.symbols.RelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.tooling.GlobalGraphOperations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexander.maven.archetypes.controllers.PersonController;
import com.alexander.maven.archetypes.domain.PersonDao;
import com.alexander.maven.archetypes.domain.graph.PersonNode;
import com.alexander.maven.archetypes.domain.graph.RootEntity;
import com.alexander.maven.archetypes.domain.graph.relationships.Rels;

@Service
public class StartUp {


	public static void main(String[] args){

		System.out.println("Starting Context");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Object bean1 = context.getBean("personDao");
		if (bean1 instanceof PersonDao){
			PersonDao dao = (PersonDao)bean1;
		} else {
			System.out.println("Have the wrong type of bean class "+bean1.getClass().getSimpleName());
		}
		
		Object bean2 = context.getBean("controller");
		if (bean2 instanceof PersonController){
			PersonController controller = (PersonController)bean2;
			
			controller.setupDatabase();
			
		} else {
			System.out.println("Have the wrong type of bean class "+bean2.getClass().getSimpleName());
		}
		
//		Object serviceObject = context.getBean("graphDatabaseService");
//		StartUp startup = new StartUp();
//		startup.setupDb(serviceObject);
		
		System.out.println("Finished context");
	}
	

	public StartUp(){
		
	}
	
	@Transactional
	public void setupDb(Object serviceObject) {
		Neo4jTemplate template;
//		GraphDatabaseService service = new EmbeddedGraphDatabase("");
		if (serviceObject instanceof EmbeddedGraphDatabase){
			GraphDatabaseService service = (EmbeddedGraphDatabase)serviceObject;
			Transaction transaction 	 = service.beginTx();
			try {
				System.out.println("Found the graph database service");
				template 			= new Neo4jTemplate(service);
				System.out.println(template);
				PersonNode alex 	= new PersonNode("JK168376A", "Alex","Hopgood");
				PersonNode chris 	= new PersonNode("JK168377B", "Chris","Hopgood");

				Node refNode		= service.getReferenceNode();
//				alex.setRefNode(new ReferenceNode(refNode));
				
				template.save(alex);
				template.save(chris);
				PersonNode found = template.findOne(alex.getNodeId(), PersonNode.class);
				
				System.out.println(found.getFirstName());
				
				GlobalGraphOperations global =  GlobalGraphOperations.at(service);
				
				Iterator<Node> nodeIter = global.getAllNodes().iterator();
				int count = 0;
				while(nodeIter.hasNext()){
					count++;
					Node node 						= nodeIter.next();
					StringBuilder builder 			= new StringBuilder();
					builder.append("Count "+count+" Node Id "+node.getId()+" ");//+" firstName "+node.getProperty("firstName"));
					Iterator<String> properties  	= node.getPropertyKeys().iterator();
					builder.append("Properties ");
					while (properties.hasNext()){
						String property = properties.next();
						builder.append("Property "+property+" value "+node.getProperty(property)+" ");
					}
					System.out.println(builder.toString());
				}
//				Node node = service.createNode();
//				node.setProperty("label", "My First Node");
				System.out.println("Reference node id "+service.getReferenceNode().getId());	
//				service.getReferenceNode().createRelationshipTo(node, Rels.RELATED);
				
				transaction.success();
			} finally {
				transaction.finish();
			}
			
//			try {
//				Thread.sleep(600000);
//			} catch (InterruptedException ie){
//				System.out.println("Couldn't sleep");
//			}
			
			service.shutdown();
		} else {
			System.out.println("Couldn't create the neo4j template");
		}
		
	}
}
