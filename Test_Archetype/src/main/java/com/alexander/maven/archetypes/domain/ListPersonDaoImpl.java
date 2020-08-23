/**
 * Copyright (c) 2015 Alexander Hopgood
 */
package com.alexander.maven.archetypes.domain;

import java.util.LinkedList;
import java.util.List;

public class ListPersonDaoImpl implements PersonDao {

	protected List<Person> persons = new LinkedList<Person>();
	
	/**
	 * Should find a person by their national insurance number
	 */
	public Person findPersonByNationalInsuranceNumber(String insuranceNumber) {
		Person toFind	= new Person(insuranceNumber,"","");
		Person found 	= null;
		if (persons.contains(toFind)){
			int index = persons.indexOf(toFind);
			found = persons.get(index);
		}
		return found;
	}

	public boolean addPerson(Person person) {
		if (person == null) return false;
		if (person.getNationalInsuranceNumber() == null) return false;
		if (persons.contains(person)){
			return false;
		} else {
			return persons.add(person);
		}
	}

}
