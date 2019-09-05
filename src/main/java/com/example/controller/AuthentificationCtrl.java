package com.example.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.Collegue;
import com.example.repository.CollegueRepository;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(value = "/auth")
public class AuthentificationCtrl {
	
	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN; 
	
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE; 
	
	@Value("${jwt.secret}")
	private String SECRET; 
	
	private CollegueRepository collegueRepository; 
	
	private PasswordEncoder passwordEncoder;

	/** Constructeur
	 * @param collegueRepository
	 * @param passwordEncoder
	 */
	public AuthentificationCtrl(CollegueRepository collegueRepository, PasswordEncoder passwordEncoder) {
		super();
		this.collegueRepository = collegueRepository;
		this.passwordEncoder = passwordEncoder;
	} 
	
	
	@PostMapping
	public ResponseEntity<?> autenticate(@RequestBody InfosAuthentification infos){
		return this.collegueRepository.findByEmail(infos.getEmail())
				.filter(collegue -> passwordEncoder.matches(infos.getMotDePasse(), collegue.getMotDePasse()))
				.map(collegue -> {
					
					Map<String, Object> infosSupplementaireToken = new HashMap<>(); 
					
					infosSupplementaireToken.put("roles", collegue.getRoles());
					
					String jetonJTW = Jwts.builder()
							.setSubject(collegue.getEmail())
							.addClaims(infosSupplementaireToken)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN*1000))
							.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
							.compact(); 
					
					ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJTW)
							.httpOnly(true)
							.maxAge(EXPIRES_IN*1000)
							.path("/")
							.build(); 
					
					return ResponseEntity.ok()
							.header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
							.build();
							
				})
				
				.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()); 
	}

	@GetMapping("/user")
	public ResponseEntity<?> afficherCollegueConnecte() {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Optional<Collegue> collegueConecte = collegueRepository.findByEmail(email); 
		
		String message = null ; 
		
		if(collegueConecte.isPresent()) {
			
			message = "matricule : " + collegueConecte.get().getMatricule() + ", nom du collègue : " + collegueConecte.get().getNom() + ", prénoms du collègue : " + collegueConecte.get().getPrenoms() + " , role : " + collegueConecte.get().getRoles();  
				
		
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message); 
		
	}
	
	
}
