package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Person;

public interface PersonService {

	public Person findByName(String name);

	public List<Person> findAll();

	public Person save(Person person);

	public Person update(Person person);

	public Boolean delete(Person person);

}
