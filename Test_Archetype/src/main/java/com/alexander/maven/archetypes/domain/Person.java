package com.alexander.maven.archetypes.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A domain object for holding the information on a person.
 * Hibernate requires an empty constructor and bean style setters and getters.
 * @author Alexander
 *
 */
public class Person {
	
	private long id;
	private String nationalInsuranceNumber;
	
	public Person(){}
	
	public Person(String nationalInsuranceNumber){
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}
	
	public String getNationalInsuranceNumber(){
		return this.nationalInsuranceNumber;
	}
	
	public void setNationalInsuranceNumber(String nationalInsuranceNumber){
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}

	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return this.id;
	}
	
	@Override
	public boolean equals(Object o){
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		
		Person person = (Person)o;
		return new EqualsBuilder()
			.append(this.nationalInsuranceNumber, person.nationalInsuranceNumber)
			.isEquals();
	}

	@Override
	public int hashCode(){
		return new HashCodeBuilder(19,51)
		.append(this.nationalInsuranceNumber)
		.toHashCode();
	}
}
