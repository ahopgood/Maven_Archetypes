package com.alexander.maven.archetypes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public void setId(long id){
		this.id = id;
	}
}
