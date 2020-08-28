package com.alexander.maven.archetypes.domain.graph;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity( type = "RELATED_TO" )
public class FamilyRelation {

	@StartNode private PersonNode person1;
	@EndNode private PersonNode person2;
	private String relationType;
	
	public FamilyRelation(PersonNode person1, PersonNode person2, String relationType){
		this.person1 = person1;
		this.person2 = person2;
		this.relationType = relationType;
	}
}
