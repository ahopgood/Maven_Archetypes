/**
 * Copyright (c) 2015 Alexander Hopgood
 */
package com.alexander.maven.archetypes.domain;

public interface PersonDao {

	public Person findPersonByNationalInsuranceNumber(String insuranceNumber);
	
	public boolean addPerson(Person person);
}
