package com.alexander.maven.archetypes.domain;

import java.util.List;

import com.alexander.maven.archetypes.domain.dao.PersonDao;

public class HibernatePersonDao extends AbstractHibernateDao<Person> implements PersonDao {

	@Override
	public Person findPersonByNationalInsuranceNumber(String insuranceNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}


