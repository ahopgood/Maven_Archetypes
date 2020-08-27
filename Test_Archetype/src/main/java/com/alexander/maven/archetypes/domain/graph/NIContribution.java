package com.alexander.maven.archetypes.domain.graph;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity( type = "CONTRIBUTES" )
public class NIContribution {

	@GraphId Long id;
	@StartNode 	private PersonNode person;
	@Fetch @EndNode	private NationalInsurance nationalInsurance;
	private Date paymentDate;
	private int paymentAmount;
	
	public NIContribution(){ 	/*Required for neo4j */	}
	
	public NIContribution(PersonNode person, NationalInsurance nationalInsurance,
			Date paymentDate, int paymentAmount){
		this.person = person;
		this.nationalInsurance = nationalInsurance;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonNode getPerson() {
		return person;
	}

	public void setPerson(PersonNode person) {
		this.person = person;
	}

	public NationalInsurance getNationalInsurance() {
		return nationalInsurance;
	}

	public void setNationalInsurance(NationalInsurance nationalInsurance) {
		this.nationalInsurance = nationalInsurance;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
}
