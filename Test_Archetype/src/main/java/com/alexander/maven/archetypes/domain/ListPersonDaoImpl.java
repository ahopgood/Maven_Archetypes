package com.alexander.maven.archetypes.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListPersonDaoImpl implements PersonDao {

	protected List<Person> persons = new LinkedList<Person>();
	
	public ListPersonDaoImpl(){

	}
	
	public ListPersonDaoImpl(List<Person> persons){
		this.persons = persons;
	}
	
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
		if (person.getNationalInsuranceNumber() == null) return false;
		if (persons.contains(person)){
			return false;
		} else {
			return persons.add(person);
		}
	}
	
	public List<Person> getAll(){
		List<Person> people = new LinkedList<Person>();
		Iterator<Person> iter = persons.iterator();
		while (iter.hasNext()) {
			people.add(iter.next());
		}
		return people;
	}
}
