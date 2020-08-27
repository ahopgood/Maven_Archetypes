package com.alexander.maven.archetype.ws.cxf.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.alexander.maven.archetypes.domain.Person;

@WebService
public interface HelloService {

	@WebMethod(operationName="sayHiToName")
	String sayHi (@WebParam(name="Name") String text);
	
	@WebMethod(operationName="sayHiToPersonObject")
	String sayHiToPerson (@WebParam(name="Person") Person person);
}
