package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepositry;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

	@Autowired
	private PersonRepositry personRepositry;

	@Transactional(readOnly = true)
	@Override
	public Person findByName(String name) {
		logger.debug("Inside findOne");
		return personRepositry.getPersonByFullName(name);

	}

	@Transactional(readOnly = true)
	@Override
	public List<Person> findAll() {
		logger.debug("Inside findAll");
		List<Person> persons = new ArrayList<>();
		Iterator<Person> itr = personRepositry.findAll().iterator();
		while (itr.hasNext()) {
			persons.add(itr.next());
			return persons;
		}
		return Collections.emptyList();
	}

	@Transactional
	@Override
	public Person save(Person person) {
		logger.debug("Inside save");
		person.setCreatedDate(new Date());
		person.setUpdateDate(new Date());
		return personRepositry.save(person);

	}

	@Transactional
	@Override
	public Boolean delete(Person person) {
		logger.debug("Inside delete");
		if (personRepositry.existsById(person.getId())) {
			personRepositry.delete(person);
			return true;
		}

		return false;
	}

	@Transactional
	@Override
	public Person update(Person person) {
		logger.debug("Inside update");
		if (personRepositry.existsById(person.getId())) {
			person.setUpdateDate(new Date());
			return personRepositry.save(person);
		}
		return null;
	}

}
