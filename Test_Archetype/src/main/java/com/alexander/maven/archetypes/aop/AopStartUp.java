package com.alexander.maven.archetypes.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexander.maven.archetype.Utils;
import com.alexander.maven.archetypes.domain.ListPersonDaoImpl;
import com.alexander.maven.archetypes.domain.Person;

public class AopStartUp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Starting context for AOP tests");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Object daoObject 		= context.getBean("aopPersonDao");
		ListPersonDaoImpl dao 	= Utils.castBean(daoObject, ListPersonDaoImpl.class);
		
		Person alex = new Person("JK168376A");
		Person chris = new Person("JK168376C");
		Person nick = new Person("JK168386D");
		dao.addPerson(null);
		dao.addPerson(alex);
		dao.addPerson(chris);
		dao.addPerson(nick);
				
	}

}
