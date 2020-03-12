package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepositry;

@SpringBootApplication
@EnableNeo4jRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.model")
public class DemoApplication {

	private static final Logger log = LogManager.getLogger(DemoApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner demo(PersonRepositry personRepository) {
		return args -> {

			personRepository.deleteAll();

			Person p1 = new Person("manindra1","abc1@gmail.com","123456");
			Person p2 = new Person("manindra2","abc2@gmail.com","1234567");
			Person p3 = new Person("manindra3","abc3@gmail.com","1234568");

			List<Person> team = Arrays.asList(p1, p2, p3);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

			personRepository.save(p1);
			personRepository.save(p2);
			personRepository.save(p3);

			p1 = personRepository.getPersonByFullName(p1.getFullName());
			p1.worksWith(p2);
			p1.worksWith(p3);
			personRepository.save(p1);

			p2 = personRepository.getPersonByFullName(p2.getFullName());
			p2.worksWith(p3);
			personRepository.save(p2);

			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info("\t" + personRepository.getPersonByFullName(person.getFullName())));
		};
	}
	

}
