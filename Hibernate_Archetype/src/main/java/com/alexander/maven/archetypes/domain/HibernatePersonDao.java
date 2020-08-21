package com.alexander.maven.archetypes.domain;

import com.alexander.maven.archetypes.domain.dao.BaseDao;
import com.alexander.maven.archetypes.domain.dao.PersonDao;

public class HibernatePersonDao implements BaseDao<Person>, PersonDao {

	@Override
	public Person findPersonByNationalInsuranceNumber(String insuranceNumber) {
		
		return null;
	}

	@Override
	public boolean addPerson(Person person) {
		return false;
	}

	@Override
	public boolean save(Person t) {
		return false;
	}

	@Override
	public Person get(long id) {
		return null;
	}

}


