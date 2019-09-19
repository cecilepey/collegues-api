package com.example.exception;

/**
 * @author Cécile Peyras
 * Exception qui gère l'absence d'un collègue dans la base
 *
 */
public class CollegueNonTrouveException extends RuntimeException {
	
	/**
	 * Constructeur
	 * 
	 * @param message
	 */
	public CollegueNonTrouveException(String message) {
		super(message);

	}
	
	public CollegueNonTrouveException() {
		super();

	}

}
