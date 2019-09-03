package com.example.exception;

/**
 * @author Cécile Peyras
 * 
 * Exception qui gère l'invalidité d'un collègue lors de l'utilisation de l'API
 *
 */

public class CollegueInvalideException extends RuntimeException {
	
	/**
	 * Constructeur
	 * 
	 * @param message
	 */
	public CollegueInvalideException(String message) {
		super(message);

	}

}
