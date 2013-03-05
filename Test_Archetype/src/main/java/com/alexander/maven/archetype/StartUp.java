package com.alexander.maven.archetype;

import org.hibernate.cfg.Configuration;
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
		
		Configuration cfg = new Configuration();
		ClassPathResource hibernateConf = new ClassPathResource("hibernate.cfg.xml");
		cfg.addResource(hibernateConf.getPath());
		
	}
}
