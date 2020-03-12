package com.example.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository
public interface PersonRepositry extends Neo4jRepository<Person,Long>{

	Person getPersonByFullName(String name);
	
}
