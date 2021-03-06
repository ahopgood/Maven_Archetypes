/**
 * Copyright (c) 2015 Alexander Hopgood
 */
package com.alexander.maven.archetypes.controllers;

import com.alexander.maven.archetypes.domain.dao.PersonDao;

public class PersonController {

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
}
