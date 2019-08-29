/**
 * 
 */
package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalideException;
import com.example.exception.CollegueNonTrouveException;
import com.example.service.CollegueService;

/**
 * @author Cécile Peyras
 *
 */
@RestController
public class CollegueController {

	CollegueService colService = new CollegueService();

	@GetMapping(value = "/collegues")
	public List<String> findByNom(@RequestParam String nom) {

		List<Collegue> listeCollegue = colService.rechercherParNom(nom);

		List<String> listeMatricule = new ArrayList<String>();

		for (Collegue liste : listeCollegue) {
			listeMatricule.add(liste.getMatricule());

		}

		return listeMatricule;

	}

	@GetMapping(value = "/collegues/{matricule}")
	public Collegue findByMatricule(@PathVariable String matricule) {

		Collegue collegue = colService.rechercherParMatricule(matricule);

		return collegue;

	}

	@ExceptionHandler({ CollegueNonTrouveException.class })
	public ResponseEntity<String> errorCollegueNonTouveException() {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collègue non trouvé");

	}

	@PostMapping(value="/collegues")
	public Collegue addNewCollegue(@RequestBody Collegue collegue) {
		
		Collegue collegue1 = colService.ajouterUnCollegue(collegue); 
	
		return collegue1; 
	}
	

	@ExceptionHandler({ CollegueInvalideException.class })
	public ResponseEntity<String> errorCollegueInvalideException() {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collègue non validé");

	}
	
}
