package com.alexander.maven.archetype.ws.cxf.server;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.alexander.maven.archetypes.domain.Person;

@WebService( endpointInterface = "com.alexander.maven.archetype.ws.cxf.server.HelloService")
public class HelloServiceImpl implements HelloService {

	@Override 
	public String sayHi(String text) {
		System.out.println("Say Hi called...");
		return "Hello " + text;
	}
	
	@Override
	public String sayHiToPerson(Person person){
		return "Hello " + person.getNationalInsuranceNumber();
	}
}
