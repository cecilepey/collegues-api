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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalideException;
import com.example.exception.CollegueNonTrouveException;
import com.example.utils.DataUtils;

/**
 * @author Cécile Peyras
 *
 */
@Component
public class CollegueService {

	@Autowired
	CollegueValidator collegueValidator;

	@Autowired
	DataUtils dataUtils;

	private Map<String, Collegue> data = dataUtils.creationCollegue();

	public CollegueService() {

	}

	public List<Collegue> rechercherParNom(String nomRecherche) {

		// TODO retourner une liste de collègues dont le nom est fourni

		List<Collegue> listeCollegue = new ArrayList<>();

		for (Collegue collegue : data.values()) {
			if (collegue.getNom().equals(nomRecherche)) {
				listeCollegue.add(collegue);
			}
		}

		return listeCollegue;
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) {
		// TODO retourner le collègue dont le matricule est fourni

		Collegue collegue1 = null;

		for (Collegue collegue : data.values()) {
			if (collegue.getMatricule().equals(matriculeRecherche)) {
				collegue1 = collegue;
			}
		}

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

		data.put(collegueAAjouter.getMatricule(), collegueAAjouter);

		return collegueAAjouter;
	}

	public Collegue modifierEmail(String matricule, String email) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		Collegue collegue = null;

		return collegue;

		// TODO Modifier le collègue
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que la photoUrl commence bien par `http`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue

		Collegue collegue = null;

		return collegue;
	}

}
