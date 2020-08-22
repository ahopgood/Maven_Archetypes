package com.alexander.maven.archetypes.controllers;

import com.alexander.maven.archetypes.domain.Person;
import com.alexander.maven.archetypes.domain.PersonDao;

public class PersonController {

	public static String PERSON_NOT_FOUND = "Could not find person";
	private PersonDao personDao;
	
	public PersonController(){
		System.out.println("In PersonController constructor");
	}
	
	public void setPersonDao(PersonDao personDao){
		System.out.println("Setting the PersonDao");
		this.personDao = personDao;
	}
	
	public PersonDao getPersonDao(){
		return this.personDao;
	}

	public String getPerson(String nationalInsuranceNumber) {
		if (this.personDao == null){
			System.out.println("Person DAO is null");
		}
		Person person = this.personDao.findPersonByNationalInsuranceNumber(nationalInsuranceNumber);
		if (person == null) {
			System.out.println("Could not find a person for that insurance number");
			return PersonController.PERSON_NOT_FOUND;
		}
		return "Person :"+person.getLastName()+", "+person.getFirstName()+" #"+person.getNationalInsuranceNumber();
	}
}
