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

import com.example.zup.domain.Bet;
import com.example.zup.domain.Person;
import com.example.zup.services.BetService;
import com.example.zup.services.PersonService;

@RestController
@RequestMapping(value="/bets")
public class BetResource {
	
	@Autowired
	private BetService betService;
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/{bet}", method=RequestMethod.GET)
	public ResponseEntity<?> add(@PathVariable Integer bet) {
		
		return ResponseEntity.ok().body(betService.find(bet));
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Person person) {
		
		Person personBet = personService.findOrCreated(person.getEmail());
		
		Integer [] numbers = betService.numbersLottery();
		
		Bet betNew = new Bet(null, personBet, numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5]);
		
		Bet betCreated = betService.insert(betNew);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(betCreated.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}

