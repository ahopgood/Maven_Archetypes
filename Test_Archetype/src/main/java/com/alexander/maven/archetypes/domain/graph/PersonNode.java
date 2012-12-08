package com.alexander.maven.archetypes.domain.graph;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class PersonNode {
	@GraphId private Long id;
	private String firstName;
	private String lastName;
	
	@Fetch @RelatedToVia( elementClass = NIContribution.class, type = "CONTRIBUTES" )
	private Set<NIContribution> contribs = new HashSet<NIContribution>();
	
	@Fetch @RelatedTo( type = "RELATED", direction = Direction.BOTH )
	private Set<PersonNode> relatives = new HashSet<PersonNode>();
	
	public PersonNode(){	/*required by neo4j*/	}
	
	public PersonNode(String firstName, String lastName){
		this.firstName 					= firstName;
		this.lastName 					= lastName;
	}
	
	public NIContribution contributeNationalInsurance(NationalInsurance nationalInsurance, int amount){
		NIContribution insurance = new NIContribution(this, nationalInsurance, new Date(), amount);
		contribs.add(insurance);
		return insurance;
	}
	
	public void related(PersonNode relation){
		if (!this.relatives.contains(relation)){
			this.relatives.add(relation);
			relation.related(this);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<NIContribution> getContribs() {
		return contribs;
	}

	public void setContribs(Set<NIContribution> contribs) {
		this.contribs = contribs;
	}

	public Set<PersonNode> getRelatives() {
		return relatives;
	}

	public void setRelatives(Set<PersonNode> relatives) {
		this.relatives = relatives;
	}
}
