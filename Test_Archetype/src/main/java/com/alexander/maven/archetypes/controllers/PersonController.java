package com.alexander.maven.archetypes.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.alexander.maven.archetypes.domain.PersonDao;
import com.alexander.maven.archetypes.domain.graph.services.PersonService;

//@RequestMapping("/")
public class PersonController {

	private PersonDao personDao;
	@Autowired private PersonService personService;

	public PersonController(){
		System.out.println("In PersonController constructor");
	}
	
	public void setupDatabase(){
		System.out.println("Setting up database");
		this.personService.setupNodes();
		System.out.println(this.personService.printOffNodes());
	}
	
	public void setPersonDao(PersonDao personDao){
		System.out.println("Setting the PersonDao");
		this.personDao = personDao;
	}
	
	public PersonDao getPersonDao(){
		return this.personDao;
	}
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
}
