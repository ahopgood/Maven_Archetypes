package com.alexander.maven.archetypes.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alexander.maven.archetype.Utils;
import com.alexander.maven.archetypes.domain.ListPersonDaoImpl;
import com.alexander.maven.archetypes.domain.Person;
import com.alexander.maven.archetypes.domain.PersonDao;

public class AopStartUp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Starting context for AOP tests");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		PersonDao dao	= context.getBean("aopPersonDao", PersonDao.class);
		
		Person alex = new Person("JK168376A");
		Person chris = new Person("JK168376C");
		Person julie = new Person("JA658743B");
		Person nick = new Person("JK168386D");

		dao.addPerson(alex);
		dao.addPerson(chris);
		dao.addPerson(julie);
		dao.addPerson(null);
		
		PersonDao nonAopDao	= context.getBean("noPointcutPersonDao", PersonDao.class);
		nonAopDao.addPerson(nick);
	}

}
