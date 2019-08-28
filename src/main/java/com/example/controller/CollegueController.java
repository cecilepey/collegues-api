/**
 * 
 */
package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.Collegue;
import com.example.service.CollegueService;

/**
 * @author Cécile Peyras
 *
 */
@RestController
public class CollegueController {

	 @GetMapping(value = "/collegues")
	  public List<String> findClient(@RequestParam String nom) {
	        
		 CollegueService colService = new CollegueService(); 
		 
		 List<Collegue> listeCollegue = colService.rechercherParNom(nom); 
		 
		 List<String> listeMatricule = new ArrayList<String>(); 
		 
		 for (Collegue liste : listeCollegue) {
			 listeMatricule.add(liste.getMatricule()); 
			 
		 }
		 
		 return listeMatricule; 
		 
	
	 }
	
	
}
