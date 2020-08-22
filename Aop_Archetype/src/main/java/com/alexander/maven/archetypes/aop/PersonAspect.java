package com.alexander.maven.archetypes.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.alexander.maven.archetypes.domain.Person;

public class PersonAspect {

	public Object around(ProceedingJoinPoint pjp, Person person) throws Throwable {
		System.out.println("Around");
		if (person != null){
			if (person.getNationalInsuranceNumber().equalsIgnoreCase("JK168376A")){
				return pjp.proceed(new Object[]{new Person("AAAAA")});
			}
			if (person.getNationalInsuranceNumber().equalsIgnoreCase("JK168376C")){
				return false;
			}
		}
		return pjp.proceed();
	}
	
	public void before(Person person){
		if (person != null){
			System.out.println("Before - adding person "+person.getNationalInsuranceNumber());
		}
	}

	public void afterFinally(Person person){
		System.out.println("After Finally - The method has done it's bit");
	}
	
	public void afterReturning(boolean success, Person person){
		System.out.println("After Returning - Has the person been added? "+success);
	}
	
	public void afterThrowing(Person person){
		System.out.println("Oh look we have a problem");
	}
}
