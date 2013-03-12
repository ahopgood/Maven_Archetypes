package com.alexander.maven.archetypes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "testEntities")
public class TestEntity extends BaseEntity {

	@Column(unique=true, nullable=false, updatable=false)
	private String name;
	
	public TestEntity(String name){
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
