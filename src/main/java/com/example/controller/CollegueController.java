/**
 * 
 */
package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
 * Controller qui gère les collègues
 *
 */
@RestController
public class CollegueController {

	@Autowired
	CollegueService colService;

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
	public ResponseEntity<Object> errorCollegueNonTouveException(CollegueNonTrouveException e) {

		return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);


	}

	@PostMapping(value="/collegues")
	public Collegue addNewCollegue(@RequestBody Collegue collegue) {
		
		Collegue collegue1 = colService.ajouterUnCollegue(collegue); 
	
		return collegue1; 
	}
	

	@ExceptionHandler({ CollegueInvalideException.class })
	public ResponseEntity<Object> errorCollegueInvalideException(CollegueInvalideException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

	}
	
	@PatchMapping(value="/collegues/{matricule}")
	public Collegue modifierEmail(@PathVariable String matricule,  @RequestBody Collegue collegue) {
		
		Collegue collegue1 = null; 
		
		if(collegue.getEmail() != null) {
		collegue1 = colService.modifierEmail(matricule, collegue); 
		}
		
		if (collegue.getPhotoUrl() !=null) {
			collegue1 = colService.modifierPhotoUrl(matricule, collegue);
		}
		return collegue1; 
	}
	
}
