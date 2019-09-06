package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.repository.CollegueRepository;

import io.jsonwebtoken.Jwts;

@Component
public class AuthentificationUtil {
	
	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN; 
	
	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE; 
	
	@Value("${jwt.secret}")
	private String SECRET; 

	@Autowired
	private CollegueRepository collegueRepository; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public ResponseEntity<?> auth(InfosAuthentification infos){
		return authPartial(infos).map(token -> ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,  token).build())
				.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()); 
	}
	
	
	public Optional<String> authPartial (InfosAuthentification infos){
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
					
					return tokenCookie.toString(); 
				});
				
				
	}


}
