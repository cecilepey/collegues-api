package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.Collegue;
import com.example.repository.CollegueRepository;

import io.jsonwebtoken.Jwts;

@Controller
public class AuthentificationCtrl {

	@Autowired
	private CollegueRepository collegueRepository;

	@Autowired
	private AuthentificationUtil authentificationUtil;

	@GetMapping("/auth")
	public String auth(Model model) {

		model.addAttribute("login", new InfosAuthentification());
		return "login";
	}

	@PostMapping("/auth")
	@ResponseBody
	public String post(@RequestBody InfosAuthentification infos, HttpServletResponse response) {

		Optional<String> token = authentificationUtil.authPartial(infos);

		if (token.isPresent()) {
			response.addHeader("set-cookie", token.get());
		}else {
			throw new RuntimeException();
		}

		return "home";
	}

	@GetMapping("/user")
	@ResponseBody
	public ResponseEntity<?> afficherCollegueConnecte() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Optional<Collegue> collegueConecte = collegueRepository.findByEmail(email);

		String message = null;

		if (collegueConecte.isPresent()) {

			message = "matricule : " + collegueConecte.get().getMatricule() + ", nom du collègue : "
					+ collegueConecte.get().getNom() + ", prénoms du collègue : " + collegueConecte.get().getPrenoms()
					+ " , role : " + collegueConecte.get().getRoles();

		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);

	}

}
