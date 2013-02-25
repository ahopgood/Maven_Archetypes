package com.alexander.maven.archetype.ws.cxf.server;

import javax.jws.WebService;

@WebService
public interface HelloService {

	String sayHi (String text);
}
