
package com.example.zup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zup.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

	List<Person> findByEmail(String email);

}
