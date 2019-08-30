package com.example.service;

import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalideException;
import com.example.repository.CollegueRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegueServiceTest {

	@Autowired
	CollegueService collegueService;
	
	@Autowired
	CollegueRepository collegueRepo; 

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueNom2CaracteresErreur() {

		Collegue collegue = new Collegue("u", "frjieoj", "jfior@fre.com", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);

	}

	@Test
	public void testAjouterUnCollegueNom2CaracteresOk() {

		Collegue collegue = new Collegue("ufreg", "frjieoj", "jfior@fre.com", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);

		Assert.assertNotNull(collegue);

	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnColleguePrenom2CaracteresErreur() {

		Collegue collegue = new Collegue("getrhu", "f", "jfior@fre.com", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);

	}
	
	@Test()
	public void testAjouterUnColleguePrenom2CaracteresOk() {

		Collegue collegue = new Collegue("getrhu", "fgfgf", "jfior@fre.com", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);
		
		Assert.assertNotNull(collegue);


	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueEmailTropCourtErreur() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "@f", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);

	}

	@Test()
	public void testAjouterUnCollegueEmailTropCourtok() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "dsgg@grgf", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);

		Assert.assertNotNull(collegue);
	}
	
	
	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueEmailSansAtErreur() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffhrie.com", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);

	}
	
	@Test()
	public void testAjouterUnCollegueEmailSansAtOk() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffhrie.com", LocalDate.of(2000, 1, 1), "http://...");

		collegueService.ajouterUnCollegue(collegue);
		
		Assert.assertNotNull(collegue);

	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueUrlSansHttpErreur() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2000, 1, 1), "//...");

		collegueService.ajouterUnCollegue(collegue);

	}

	@Test()
	public void testAjouterUnCollegueUrlSansHttpOk() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2000, 1, 1), "//...");

		collegueService.ajouterUnCollegue(collegue);
		
		Assert.assertNotNull(collegue);

	}
	
	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueAge18AnsErreur() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2010, 1, 1), "http//...");

		collegueService.ajouterUnCollegue(collegue);

	}
	
	@Test()
	public void testAjouterUnCollegueAge18AnsOk() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2010, 1, 1), "http//...");

		collegueService.ajouterUnCollegue(collegue);
		
		Assert.assertNotNull(collegue);

	}
	
	@Test
	public void testRechercherParNom() {
		
		
	}

	@Test
	public void testRechercherParMatricule() {
		fail("Not yet implemented");
	}


	@Test
	public void testModifierEmail() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierPhotoUrl() {
		fail("Not yet implemented");
	}



}
