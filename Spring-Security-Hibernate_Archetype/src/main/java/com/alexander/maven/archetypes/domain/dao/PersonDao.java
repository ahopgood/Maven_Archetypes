package com.alexander.maven.archetypes.domain.dao;

import com.alexander.maven.archetypes.domain.Person;

public interface PersonDao extends BaseDao<Person> {

	public Person findPersonByNationalInsuranceNumber(String insuranceNumber);
}
