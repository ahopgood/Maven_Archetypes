package com.alexander.maven.archetypes.controllers;

import com.alexander.maven.archetypes.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PersonController {

	private PersonDao personDao;
	
	public PersonController(){
		System.out.println("In PersonController constructor");
	}

	@Qualifier("listPersonDaoImpl")
	@Autowired
	public void setPersonDao(PersonDao personDao){
		System.out.println("Setting the PersonDao");
		this.personDao = personDao;
	}
	
	public PersonDao getPersonDao(){
		return this.personDao;
	}
}
