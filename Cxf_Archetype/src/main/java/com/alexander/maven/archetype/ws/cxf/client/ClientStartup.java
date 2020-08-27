package com.alexander.maven.archetype.ws.cxf.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexander.maven.archetype.ws.cxf.server.HelloService;
import com.alexander.maven.archetypes.domain.Person;

public class ClientStartup {

	public static void main(String[] args){
		System.out.println("Starting client context");
		ApplicationContext context = new ClassPathXmlApplicationContext("clientApplicationContext.xml");
		
		HelloService service = context.getBean("helloClient", HelloService.class);
		System.out.println(service.sayHi("Captain Apollo"));
		
		System.out.println(service.sayHiToPerson(new Person("JK168376A")));
	}
}
