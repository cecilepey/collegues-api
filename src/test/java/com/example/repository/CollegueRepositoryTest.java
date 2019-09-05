package com.example.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entite.Collegue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CollegueRepositoryTest {

	@Autowired
	PasswordEncoder passwordEncoder; 
	
	@Autowired
	private CollegueRepository collegueRepo;

	@Test
	public void testFindByNom() {

		String matricule = UUID.randomUUID().toString();

		collegueRepo.save(new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN")));
		List<Collegue> liste = collegueRepo.findByNom("Peyras");

		Assert.assertEquals(1, liste.size());
	}
	
	@Test
	public void testFindByNomNonTrouve() {

		String matricule = UUID.randomUUID().toString();

		collegueRepo.save(new Collegue(matricule, "Peyrs", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN")));
		List<Collegue> liste = collegueRepo.findByNom("Peyras");

		Assert.assertEquals(0, liste.size());
	}


	@Test
	public void testFindByMatricule() {
		
		String matricule = UUID.randomUUID().toString();

		collegueRepo.save(new Collegue(matricule, "Peyrs", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN")));
		
		Collegue collegue = collegueRepo.findByMatricule(matricule);
		
		Assert.assertNotNull(collegue);

	}
	
	@Test
	public void testFindByMatriculeNonTrouve() {
		
		String matricule = UUID.randomUUID().toString();

		collegueRepo.save(new Collegue(matricule, "Peyrs", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN")));
		
		Collegue collegue = collegueRepo.findByMatricule("12542");
		
		Assert.assertEquals(null, collegue);

	}

}
