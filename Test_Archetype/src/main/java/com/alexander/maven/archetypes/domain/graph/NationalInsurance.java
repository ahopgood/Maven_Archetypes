package com.alexander.maven.archetypes.domain.graph;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

@NodeEntity
public class NationalInsurance {

	@GraphId private Long id;
	private String nationalInsuranceNumber;
	
	@Fetch @RelatedToVia( elementClass = NIContribution.class, type = "CONTRIBUTES ", direction = Direction.INCOMING )
	private Set<NIContribution> contributions = new HashSet<NIContribution>();
	
	public NationalInsurance(){		/*Required by neo4j*/	}
	
	public NationalInsurance(String nationalInsuranceNumber){
		this.nationalInsuranceNumber 	= nationalInsuranceNumber;
	}
	
	public void contribution(NIContribution contribution){
		this.contributions.add(contribution);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNationalInsuranceNumber() {
		return nationalInsuranceNumber;
	}

	public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}

}
