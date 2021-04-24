package com.example.zup.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.zup.domain.Bet;
import com.example.zup.domain.Person;
import com.example.zup.repositories.BetRepository;
import com.example.zup.repositories.PersonRepository;

@Service
public class BetService {
	
	@Autowired
	private BetRepository repo;

	public Bet find(Integer id) {
		Optional<Bet> objeto = repo.findById(id);
		return objeto.orElse(null);
	}
	
	public Bet insert(Bet objeto) {
		return repo.saveAndFlush(objeto);
	}
	
	public Integer[] numbersLottery() {
		// declare and create an Array of 6 integers
		Integer[] numbers = new Integer[6];
		
		int flag = 0;
		
		// initialize 
		for(int i = 0; i < 6; i++) {
			flag = 0;
			
			while(flag == 0) {
				numbers[i] = 1 + (int)(Math.random()*60);
				
				flag = 1;
				
				for(int j = 0; j < i; j++) {
					if(numbers[i] == numbers[j]) {
						flag = 0;
					}
				}
			}
		}
		
		return numbers;
	}

}
