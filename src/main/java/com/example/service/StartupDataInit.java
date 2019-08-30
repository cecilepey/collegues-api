/**
 * 
 */
package com.example.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.entite.Collegue;
import com.example.repository.CollegueRepository;

/**
 * @author Cécile Peyras
 *
 */
@Component
public class StartupDataInit {
	@Autowired
	CollegueRepository collegueRepo;

	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		// TODO insérer des collègues en base de données

		Collegue collegue1 = new Collegue(UUID.randomUUID().toString(), "Peyras", "Cecile", "cecile@collegue.fr",
				LocalDate.of(1981, 7, 15), "http/photo");

		Collegue collegue2 = new Collegue(UUID.randomUUID().toString(), "Lili", "Saina", "saina@collegue.fr",
				LocalDate.of(1980, 9, 15), "http/photo");

		Collegue collegue3 = new Collegue(UUID.randomUUID().toString(), "Toto", "Titi", "titi@collegue.fr",
				LocalDate.of(1983, 6, 15), "http/photo"); 
		
		collegueRepo.save(collegue1); 
		collegueRepo.save(collegue2); 
		collegueRepo.save(collegue3); 
	}
}
