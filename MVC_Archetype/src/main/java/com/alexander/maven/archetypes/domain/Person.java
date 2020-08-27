/**
 * Copyright (c) 2015 Alexander Hopgood
 */
package com.alexander.maven.archetypes.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A domain object for holding the information on a person.
 * @author Alexander
 *
 */
public class Person {

	private String nationalInsuranceNumber;
	private String firstName;
	private String lastName;
	
	public Person(String nationalInsuranceNumber, String firstName, String lastName){
		this.nationalInsuranceNumber = nationalInsuranceNumber;
		this.firstName 	= firstName;
		this.lastName 	= lastName;
	}
	
	public String getNationalInsuranceNumber(){
		return this.nationalInsuranceNumber;
	}
	
	public void setNationalInsuranceNumber(String nationalInsuranceNumber){
		this.nationalInsuranceNumber = nationalInsuranceNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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
	
	@Override
	public String toString(){
		return new ToStringBuilder(this)
		.append(this.lastName)
		.append(this.firstName)
		.append(this.nationalInsuranceNumber)
		.toString();
	}
}
