package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entite.Collegue;

/**
 * 
 * @author Cécile Peyras
 * Interface qui gère les méthode qui font des traitements sur les collègues
 *
 */
public interface CollegueRepository extends JpaRepository<Collegue, String> {
	
	 /**
	  * Méthode qui permet d'afficher une liste de collègue par le nom
	 * @param nom
	 * @return
	 */
	List<Collegue> findByNom(String nom);
	 
	 /**
	  * Méthode qui retourne un Collègue grace à son matricule
	 * @param matricule
	 * @return
	 */
	Collegue findByMatricule(String matricule);

	
}
