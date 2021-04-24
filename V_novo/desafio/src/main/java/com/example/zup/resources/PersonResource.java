package com.example.zup.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.zup.domain.Person;
import com.example.zup.services.PersonService;

@RestController
@RequestMapping(value="/people")
public class PersonResource {
	
	@Autowired
	private PersonService service;

	
	@RequestMapping(value="/{email}", method=RequestMethod.GET)
	public ResponseEntity<?> listarPorEmail(@PathVariable String email) {
		
		return ResponseEntity.ok().body(service.findByEmail(email));
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Person person) {
		
		Person personNew = new Person(null, person.getEmail());
		
		Person personCreated = service.insert(personNew);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(personCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}

