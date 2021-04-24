package com.nelioalves.cursomc.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Lottery;
import com.neolioalves.cursomc.services.LotteryNumbers;

@RestController
@RequestMapping(value="/{email}")
public class LotteryResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public Lottery bet(@PathVariable String email) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		LotteryNumbers numbersLottery = new LotteryNumbers();
		
		Lottery myBet = new Lottery(null, email, numbersLottery.execute());
		
		em.getTransaction().begin();
		
		em.persist(myBet);
		
		System.out.println(myBet);
		
		em.getTransaction().commit();

		System.out.println("Pronto!");
		
		em.close();
		emf.close();
		
		return myBet;
	}
	
}
