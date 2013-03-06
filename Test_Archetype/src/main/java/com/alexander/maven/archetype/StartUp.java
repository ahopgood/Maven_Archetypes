package com.alexander.maven.archetype;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.alexander.maven.archetypes.controllers.PersonController;
import com.alexander.maven.archetypes.domain.HibernatePersonDao;
import com.alexander.maven.archetypes.domain.Person;
import com.alexander.maven.archetypes.domain.dao.PersonDao;

public class StartUp {

	public static void main(String[] args){

		System.out.println("Starting Context");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Object bean1 = context.getBean("personDao");
		if (bean1 instanceof PersonDao){
			PersonDao dao = (PersonDao)bean1;
		} else {
			System.out.println("Have the wrong type of bean class "+bean1.getClass().getSimpleName());
		}
		
		Object bean2 = context.getBean("controller");
		if (bean2 instanceof PersonController){
			PersonController controller = (PersonController)bean2;
		} else {
			System.out.println("Have the wrong type of bean class "+bean2.getClass().getSimpleName());
		}

		PersonDao personDao = context.getBean(HibernatePersonDao.class);
		System.out.println(personDao);
		personDao.save(new Person("JK168376A"));
	}
	
	public static void checkHibernate(){
		Properties jdbcProps = new Properties();
		try{
			jdbcProps.load(new BufferedReader(new FileReader("src/main/filters/jdbc-filter.properties")));
		} catch (IOException ioe){ 
			ioe.printStackTrace();
		}
		
		ClassPathResource hibernateConf = new ClassPathResource("hibernate.cfg.xml");
		try {
			System.out.println(hibernateConf.getURL().toString());
		} catch (Exception e){
			e.printStackTrace();
		}
		SessionFactory sessionFactory = new Configuration()
			.addAnnotatedClass(Person.class)
			.setProperty("hibernate.hbm2ddl.auto", "create")
			.addResource(hibernateConf.getPath())
			.buildSessionFactory(new ServiceRegistryBuilder()
				.applySettings(jdbcProps)
				.buildServiceRegistry()
			);	
		Session session = sessionFactory.openSession();//sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();		
		Person person1 = new Person();
		person1.setNationalInsuranceNumber("JK168376A");

		Long personid = (Long)session.save(person1);
		System.out.println(personid);
		
//		session.load(Person.class, 1);
		Object personObj = session.get(Person.class, (long)1);
		
		System.out.println(personObj);
		
		Person domainPerson = (Person)personObj;
		System.out.println(domainPerson.getNationalInsuranceNumber());
		
		transaction.commit();
		session.close();
	}
}
