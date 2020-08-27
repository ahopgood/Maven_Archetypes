/**
 * Copyright (c) 2015 Alexander Hopgood
 */
package com.alexander.maven.archetypes.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alexander.maven.archetypes.domain.Person;
import com.alexander.maven.archetypes.domain.PersonDao;

@Controller
@RequestMapping("/persons/*")
public class PersonController {

	protected static final String PERSON_NOT_FOUND = "Person could not be found.";

	
	@RequestMapping("add")
	public @ResponseBody String addPerson(@RequestParam String insuranceNumber, 
			@RequestParam String firstName, 
			@RequestParam String lastName){
		System.out.println("In the addPerson call");
		Person person = new Person(insuranceNumber, firstName, lastName);
		return ""+this.personDao.addPerson(person);
	}
	
	@RequestMapping("get")
	public @ResponseBody String getPerson(@RequestParam String insuranceNumber){
		Person person = this.findPerson(insuranceNumber);
		if (person == null) {
			System.out.println("Could not find a person for that insurance number");
			return PersonController.PERSON_NOT_FOUND;
		}
		System.out.println("Found a person");
		return "Person :"+person.getLastName()+", "+person.getFirstName()+" #"+person.getNationalInsuranceNumber();
	}
	
	@RequestMapping("getAll")
	public @ResponseBody String getAll(){
		if (this.personDao == null){
			System.out.println("Person DAO is null");
		}
		List<Person> people = this.personDao.getAll();
		StringBuilder builder = new StringBuilder();
		for (Person person : people){
			builder.append(person.getNationalInsuranceNumber()+" ");
		}
		return builder.toString();
	}
	
	@RequestMapping("put")
	public @ResponseBody String addPerson(){
		return "";
	}
	
	@RequestMapping("staticGet")
	public @ResponseBody String getStatic(){
		return "Static get";
	}
	
	@RequestMapping("jsonGet")
	public @ResponseBody Person getJsonPerson(@RequestParam String insuranceNumber){
		Person person = this.findPerson(insuranceNumber);
		if (person == null) {
			System.out.println("Could not find a person for that insurance number");
			//create a new empty person?
		}
		return person;
	}
	
	@RequestMapping(value = "addJson", method = RequestMethod.POST)
	public @ResponseBody Person addPerson(@RequestBody Person person){
		//adding @RequestBody will stop the controller from trying to extract args as a form parameter and instead as a json obj
		System.out.println("In the add Json Person call "+person);
		boolean result = this.personDao.addPerson(person);
		if (result){
			return person;
		} else {
			return null;
		}
	}
	
	protected Person findPerson(String insuranceNumber){
		if (this.personDao == null){
			System.out.println("Person DAO is null");
		}
		System.out.println("Looking for insurance number "+insuranceNumber);
		Person person = this.personDao.findPersonByNationalInsuranceNumber(insuranceNumber);
		return person;
	}
	
	/* Standard setters, getters and constructors */
	private PersonDao personDao;
	
	public PersonController(){
		System.out.println("In PersonController constructor");
	}
	
	public void setPersonDao(@RequestParam PersonDao personDao){
		System.out.println("Setting the PersonDao "+personDao);
		this.personDao = personDao;
	}
	
	public PersonDao getPersonDao(){
		return this.personDao;
	}
}
