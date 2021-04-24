package com.example.zup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zup.domain.Bet;
import com.example.zup.domain.Person;
import com.example.zup.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repo;

	public Person find(Integer id) {
		Optional<Person> objeto = repo.findById(id);
		return objeto.orElse(null);
	}
	
	public Person insert(Person objeto) {
		return repo.saveAndFlush(objeto);
	}
	
	public List<Person> findByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	public Person findOrCreated(String email) {
		List<Person> objeto = repo.findByEmail(email);
	
		if( objeto.isEmpty() ) {
			Person newPerson = new Person(null, email);
			Person personFlushed = this.insert(newPerson);
			return personFlushed;
		} else {
			return objeto.get(0);
		}
	}

}
