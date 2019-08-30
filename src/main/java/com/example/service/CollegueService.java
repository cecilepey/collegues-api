/**
 * 
 */
package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalideException;
import com.example.exception.CollegueNonTrouveException;
import com.example.repository.CollegueRepository;
import com.example.utils.DataUtils;

/**
 * @author Cécile Peyras
 *
 */
@Service
@Transactional
public class CollegueService {


	CollegueValidator collegueValidator;

	@Autowired
	CollegueRepository collegueRepo;
	
	DataUtils dataUtils;

	//private Map<String, Collegue> data;

	public CollegueService(CollegueValidator collegueValidator, DataUtils dataUtils) {

		super();
		this.collegueValidator = collegueValidator;
		this.dataUtils = dataUtils;

	}
//
//	@PostConstruct
//	public void init() {
//		
//		//this.data = dataUtils.creationCollegue();
//	}

	public List<Collegue> rechercherParNom(String nomRecherche) {

		// TODO retourner une liste de collègues dont le nom est fourni

		List<Collegue> listeCollegue = collegueRepo.findByNom(nomRecherche);

//		for (Collegue collegue : data.values()) {
//			if (collegue.getNom().equals(nomRecherche)) {
//				listeCollegue.add(collegue);
//			}
//		}

		return listeCollegue;
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) {
		// TODO retourner le collègue dont le matricule est fourni

		Collegue collegue1 = collegueRepo.findByMatricule(matriculeRecherche); 

//		for (Collegue collegue : data.values()) {
//			if (collegue.getMatricule().equals(matriculeRecherche)) {
//				collegue1 = collegue;
//			}
//		}

		if (collegue1 != null) {
			return collegue1;
		} else {
			throw new CollegueNonTrouveException("Collègue non trouvé");
		}

	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) {

		collegueValidator.validerCollegue(collegueAAjouter);

		// TODO Si une des règles ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`.

		// TODO générer un matricule pour ce collègue (`UUID.randomUUID().toString()`)
		collegueAAjouter.setMatricule(UUID.randomUUID().toString());

		// TODO Sauvegarder le collègue

		collegueRepo.save(collegueAAjouter);

		return collegueAAjouter;
	}

	public Collegue modifierEmail(String matricule, String email) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

				Collegue collegue1 = collegueRepo.findByMatricule(matricule); 

//		for (Collegue collegue : data.values()) {
//			if (collegue.getMatricule().equals(matricule)) {
//				collegue1 = collegue;
//			}
//		}

		if (email != null) {
			if (email.length() < 3) {
				throw new CollegueInvalideException("L'email est trop court");
			}
			if (!email.contains("@")) {
				throw new CollegueInvalideException("L'email n'a pas d'@");
			}
		} else {
			throw new CollegueInvalideException("L'email est vide");
		}

		// if (collegue1 != null) {
		collegue1.setEmail(email);
		return collegue1;
//		} else {
//			throw new CollegueNonTrouveException("Collègue non trouvé");
//		}

		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		Collegue collegue1 = collegueRepo.findByMatricule(matricule); 

//		for (Collegue collegue : data.values()) {
//			if (collegue.getMatricule().equals(matricule)) {
//				collegue1 = collegue;
//			}
//		}

		// TODO Vérifier que la photoUrl commence bien par `http`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		if (photoUrl != null) {
			if (!photoUrl.startsWith("http")) {
				throw new CollegueInvalideException("L'url ne commence pas par http");
			}
		} else {
			throw new CollegueInvalideException("L'url est vide");
		}

		// TODO Modifier le collègue

		collegue1.setPhotoUrl(photoUrl);

		return collegue1;
	}

}
