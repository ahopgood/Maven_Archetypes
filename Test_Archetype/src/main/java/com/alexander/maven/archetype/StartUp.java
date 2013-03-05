package com.alexander.maven.archetype;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.alexander.maven.archetypes.controllers.PersonController;
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
		
		Properties jdbcProps = new Properties();
		try{
			jdbcProps.load(new ClassPathResource("src/main/filters/jdbc-filter.properts").getInputStream());
		} catch (IOException ioe){  }
		
		ClassPathResource hibernateConf = new ClassPathResource("hibernate.cfg.xml");
		SessionFactory session = new Configuration()
			.addResource(hibernateConf.getPath())
			.buildSessionFactory(new ServiceRegistryBuilder()
				.applySettings(jdbcProps)
				.buildServiceRegistry()
			);
		
	}
}
