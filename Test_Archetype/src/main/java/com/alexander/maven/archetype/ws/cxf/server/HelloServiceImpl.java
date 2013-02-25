package com.alexander.maven.archetype.ws.cxf.server;

import javax.jws.WebService;

@WebService( endpointInterface = "com.alexander.maven.archetype.ws.cxf.server.HelloService")
public class HelloServiceImpl implements HelloService {

	@Override 
	public String sayHi(String text) {
		System.out.println("Say Hi called...");
		return "Hello " + text;
	}
}
