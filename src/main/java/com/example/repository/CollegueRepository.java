package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {
	
	 List<Collegue> findByNom(String nom);
	 
	 Collegue findByMatricule(String matricule);

	
}
