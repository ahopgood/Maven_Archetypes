package com.alexander.maven.archetypes.domain.graph;

import java.util.Date;

public class NationalInsurance {

	private PersonNode person;
	private int amount;
	private Date period;
	
	public NationalInsurance(PersonNode person, Date period, int amount){
		this.person = person;
		this.period = period;
		this.amount	= amount;
	}
}
