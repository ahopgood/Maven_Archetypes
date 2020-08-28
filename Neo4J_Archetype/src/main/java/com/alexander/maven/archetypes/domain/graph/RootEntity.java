package com.alexander.maven.archetypes.domain.graph;

import org.neo4j.graphdb.Node;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class RootEntity {
	@GraphId private Long id;
	String rootName;
	
	public RootEntity(){}
	
	public Long getId(){
		return id;
	}
	
	public void setRootName(String rootName){
		this.rootName = rootName;
	}
	
	public String getRootName(){
		return this.rootName;
	}
}
