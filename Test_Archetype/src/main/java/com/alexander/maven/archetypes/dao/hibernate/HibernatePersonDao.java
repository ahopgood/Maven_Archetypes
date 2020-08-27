package com.alexander.maven.archetypes.dao.hibernate;

import com.alexander.maven.archetypes.dao.PersonDao;
import com.alexander.maven.archetypes.domain.Person;

public class HibernatePersonDao extends AbstractHibernateDao<Person> implements PersonDao {

	public HibernatePersonDao(){
		super(Person.class);
	}
	
	@Override
	public Person findPersonByNationalInsuranceNumber(String insuranceNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}


