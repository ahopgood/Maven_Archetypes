package com.alexander.maven.archetypes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alexander.maven.archetypes.domain.Person;
import com.alexander.maven.archetypes.domain.PersonDao;

@Controller
@RequestMapping("/persons/")
public class PersonController {

	@RequestMapping("add")
	public boolean addPerson(@RequestParam String insuranceNumber, 
			@RequestParam String firstName, 
			@RequestParam String secondName){
		Person person = new Person(insuranceNumber, firstName, secondName);
		return this.personDao.addPerson(person);
	}
	
	@RequestMapping("get")
	public @ResponseBody String getPerson(@RequestParam String insuranceNumber){
		Person person = this.personDao.findPersonByNationalInsuranceNumber(insuranceNumber);
		return "Person :"+person.getLastName()+", "+person.getFirstName()+" #"+person.getNationalInsuranceNumber();
	}
	
	@RequestMapping("staticGet")
	public @ResponseBody String getStatic(){
		return "Static get";
	}
	
	private PersonDao personDao;
	
	public PersonController(){
		System.out.println("In PersonController constructor");
	}
	
	public void setPersonDao(@RequestParam PersonDao personDao){
		System.out.println("Setting the PersonDao");
		this.personDao = personDao;
	}
	
	public PersonDao getPersonDao(){
		return this.personDao;
	}
}
