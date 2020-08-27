package com.alexander.maven.archetypes.domain.dao;

import com.alexander.maven.archetypes.domain.Person;

public interface PersonDao {

	public Person findPersonByNationalInsuranceNumber(String insuranceNumber);
	
	public boolean addPerson(Person person);
}
