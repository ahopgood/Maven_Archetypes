package com.alexander.maven.archetypes.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.NaturalId;

/**
 * A domain object for holding the information on a person.
 * Hibernate requires an empty constructor and bean style setters and getters.
 * @author Alexander
 *
 */
@Entity
@Table(name="Persons")
@SuppressWarnings("serial")
public class Person implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="id", unique=true, nullable=false, updatable=false)
	private Long id;
	
	@Column (name="nationalInsuranceNumber", unique=true, nullable=false, updatable=false)
	private String nationalInsuranceNumber;
	private String firstName;
	private String lastName;
	
	public Person(){}
	
	public Person(String nationalInsuranceNumber, String firstName, String lastName){
		this.nationalInsuranceNumber = nationalInsuranceNumber;
		this.firstName 	= firstName;
		this.lastName 	= lastName;
	}
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
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
