package com.example.service;

import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalideException;
import com.example.exception.CollegueNonTrouveException;
import com.example.repository.CollegueRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegueServiceTest {

	@Autowired
	PasswordEncoder passwordEncoder; 
	
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

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2000, 1, 1), "http://...");

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

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2000, 1, 1), "http//...");

		collegueService.ajouterUnCollegue(collegue);

		Assert.assertNotNull(collegue);

	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjouterUnCollegueAge18AnsErreur() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2015, 1, 1), "http//...");

		collegueService.ajouterUnCollegue(collegue);

	}

	@Test()
	public void testAjouterUnCollegueAge18AnsOk() {

		Collegue collegue = new Collegue("getrhu", "fgyuf", "jffh@rie.com", LocalDate.of(2000, 1, 1), "http//...");

		collegueService.ajouterUnCollegue(collegue);

		Assert.assertNotNull(collegue);

	}

	@Test
	public void testRechercherParNomOK() {
		Collegue collegue = new Collegue(UUID.randomUUID().toString(), "Lili", "Cecile", "cecile@collegue.fr",
				LocalDate.of(1981, 7, 15), "http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));
		List<Collegue> liste = new ArrayList<>();

		liste.add(collegue);

		liste = collegueService.rechercherParNom("Lili");

		Assert.assertEquals(1, liste.size());

	}

	@Test
	public void testRechercherParNomErreur() {
		Collegue collegue = new Collegue(UUID.randomUUID().toString(), "Peyras", "Cecile", "cecile@collegue.fr",
				LocalDate.of(1981, 7, 15), "http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));
		List<Collegue> liste = new ArrayList<>();

		liste.add(collegue);

		liste = collegueService.rechercherParNom("Peas");

		Assert.assertEquals(0, liste.size());

	}

	@Test(expected = CollegueNonTrouveException.class)
	public void testRechercherParMatriculeErreur() {
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);

		collegue = collegueService.rechercherParMatricule("aaa");

	}

	@Test
	public void testRechercherParMatriculeOk() {
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);

		Collegue collegue1 = collegueService.rechercherParMatricule(matricule);

		Assert.assertNotNull(collegue1);

	}

	@Test
	public void testModifierEmailOK() {
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);
		
		Collegue collegue1 = new Collegue();

		collegue1.setEmail("lili@collegue.fr");

		collegue = collegueService.modifierEmail(matricule, collegue1);
		
		Assert.assertEquals("lili@collegue.fr", collegue.getEmail());
	}
	
	@Test (expected = CollegueInvalideException.class)
	public void testModifierEmailTropCourt() {
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);

		Collegue collegue1 = new Collegue();

		collegue1.setEmail("k@");
		
		collegue = collegueService.modifierEmail(matricule, collegue1);
		
	}
	
	@Test (expected = CollegueInvalideException.class)
	public void testModifierEmailSansAt() {
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);

		Collegue collegue1 = new Collegue();

		collegue1.setEmail("lilicollegue.fr");


		collegue = collegueService.modifierEmail(matricule, collegue1);
		
	}
	
	@Test (expected = CollegueNonTrouveException.class)
	public void testModifierEmailMauvaisMatricule() {
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);

		Collegue collegue1 = new Collegue()
;
		collegue1.setEmail("lili@collegue.fr");
		
		 collegue = collegueService.modifierEmail("aaa", collegue1);
		
	}


	@Test
	public void testModifierPhotoUrlOk() {
		
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);
		Collegue collegue1 = new Collegue();

		collegue1.setPhotoUrl("http/joliephoto");


		collegue= collegueService.modifierPhotoUrl(matricule, collegue1);
		
		Assert.assertEquals("http/joliephoto", collegue.getPhotoUrl());
		
	}
	
	@Test (expected = CollegueInvalideException.class)
	public void testModifierPhotoUrlSansHttp() {
		
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);

		Collegue collegue1 = new Collegue();
		collegue1.setPhotoUrl("joliephoto");

		collegue = collegueService.modifierPhotoUrl(matricule, collegue1);
		
	}
	
	@Test (expected = CollegueNonTrouveException.class)
	public void testModifierPhotoUrlMauvaisMatricule() {
		
		String matricule = UUID.randomUUID().toString();

		Collegue collegue = new Collegue(matricule, "Peyras", "Cecile", "cecile@collegue.fr", LocalDate.of(1981, 7, 15),
				"http/photo", passwordEncoder.encode("test"), Arrays.asList("ROLE_ADMIN"));

		collegueRepo.save(collegue);

		Collegue collegue1 = new Collegue();

		collegue1.setPhotoUrl("http/joliePhoto");

		collegue = collegueService.modifierPhotoUrl("aaa", collegue1);
		
	}

}
