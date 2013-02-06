package com.alexander.maven.archetypes.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.alexander.maven.archetypes.domain.Person;

public class PersonAspect {

	public Object print(ProceedingJoinPoint pjp, Person person) throws Throwable {
		System.out.println("Intercept before method call!");

		if (person.getNationalInsuranceNumber().equalsIgnoreCase("JK168376A")){
			return pjp.proceed(new Object[]{new Person("AAAAA")});
		}
		return pjp.proceed();
	}
	
}
