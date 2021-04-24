
package com.example.zup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zup.domain.Bet;
import com.example.zup.domain.Person;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer>{

	List<Bet> findByPerson(Person person);
}
