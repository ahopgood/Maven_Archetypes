package com.alexander.maven.archetypes.domain;

import java.util.List;

public interface PersonDao {
	
	public Person findPersonByNationalInsuranceNumber(String insuranceNumber);
	
	public List<Person> getAll();
	
	public boolean addPerson(Person person);
}
