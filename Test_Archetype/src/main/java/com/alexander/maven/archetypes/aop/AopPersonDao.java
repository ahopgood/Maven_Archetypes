package com.alexander.maven.archetypes.aop;

import java.util.LinkedList;
import java.util.List;

import com.alexander.maven.archetypes.domain.Person;
import com.alexander.maven.archetypes.domain.PersonDao;

public class AopPersonDao implements PersonDao {

	protected List<Person> persons = new LinkedList<Person>();
	
	/**
	 * Should find a person by their national insurance number
	 */
	@Override
	public Person findPersonByNationalInsuranceNumber(String insuranceNumber) {
		Person toFind	= new Person(insuranceNumber);
		Person found 	= null;
		if (persons.contains(toFind)){
			int index = persons.indexOf(toFind);
			found = persons.get(index);
		}
		return found;
	}

	@Override
	public boolean addPerson(Person person) {
		if (person == null) throw new IllegalArgumentException("Cannot add a null person");
		if (person.getNationalInsuranceNumber() == null) return false;
		if (persons.contains(person)){
			return false;
		} else {
			System.out.println("Adding person "+person.getNationalInsuranceNumber());
			return persons.add(person);
		}
	}

}
