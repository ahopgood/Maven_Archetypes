package com.alexander.maven.archetypes.dao;

import java.util.LinkedList;
import java.util.List;

import com.alexander.maven.archetypes.domain.Person;

public class ListPersonDaoImpl implements PersonDao {

	protected List<Person> persons = new LinkedList<Person>();
	
	/**
	 * Should find a person by their national insurance number
	 */
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
	public Person get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long save(Person person) {
		if (person == null) return -1;
		if (person.getNationalInsuranceNumber() == null) return -1;
		if (persons.contains(person)){
			return -1;
		} else {
			persons.add(person);
			return persons.size();
		}
	}

	@Override
	public void delete(Person person) {
		if (person != null){
			if (person.getNationalInsuranceNumber() != null){
				if (persons.contains(person)){
					persons.remove(person);
				}//person found in persons collection
			}//insuranceNumber check
		}//null check
		
	}

}
