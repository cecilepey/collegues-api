/**
 * 
 */
package com.example.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalideException;
import com.example.repository.CollegueRepository;

/**
 * @author Cécile Peyras
 *
 */
@Component
public class CollegueValidator {
	

	public void validerCollegue(Collegue collegueAValider) {
		

		if (collegueAValider.getNom() != null) {
			if (collegueAValider.getNom().length() < 2) {
				throw new CollegueInvalideException("Le nom est trop court");
			}
		} else {
			throw new CollegueInvalideException("Le nom est vide");
		}

		if (collegueAValider.getPrenoms() != null) {
			if (collegueAValider.getPrenoms().length() < 2) {
				throw new CollegueInvalideException("Le prénom est trop court");
			}
		} else {
			throw new CollegueInvalideException("Le prénom est trop court");
		}

		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`

		if (collegueAValider.getEmail() != null) {
			if (collegueAValider.getEmail().length() < 3) {
				throw new CollegueInvalideException("L'email est trop court");
			}
			if (!collegueAValider.getEmail().contains("@")) {
				throw new CollegueInvalideException("L'email n'a pas d'@");
			}
		} else {
			throw new CollegueInvalideException("L'email est vide");
		}

		// TODO Vérifier que la photoUrl commence bien par `http`
		if (collegueAValider.getPhotoUrl() != null) {
			if (!collegueAValider.getPhotoUrl().startsWith("http")) {
				throw new CollegueInvalideException("L'url ne commence pas par http");
			}
		} else {
			throw new CollegueInvalideException("L'url est vide");
		}

		// TODO Vérifier que la date de naissance correspond à un age >= 18

		if (collegueAValider.getDateDeNaissance() != null) {
			if (collegueAValider.getDateDeNaissance().getYear() > (LocalDate.now().getYear() - 18)) {
				throw new CollegueInvalideException("L'utilisateur n'a pas 18 ans");
			}
		} else {
			throw new CollegueInvalideException("La date de naissance n'est pas correcte");
		}

	}

	public void validerEmail(Collegue collegueAValider) {

		if (collegueAValider.getEmail() != null) {
			if (collegueAValider.getEmail().length() < 3) {
				throw new CollegueInvalideException("L'email est trop court");
			}
			if (!collegueAValider.getEmail().contains("@")) {
				throw new CollegueInvalideException("L'email n'a pas d'@");
			}
		} else {
			throw new CollegueInvalideException("L'email est vide");
		}

	}

	public void validerPhotoUrl(Collegue collegueAValider) {
		if (collegueAValider.getPhotoUrl() != null) {
			if (!collegueAValider.getPhotoUrl().startsWith("http")) {
				throw new CollegueInvalideException("L'url ne commence pas par http");
			}
		} else {
			throw new CollegueInvalideException("L'url est vide");
		}

	}

}
