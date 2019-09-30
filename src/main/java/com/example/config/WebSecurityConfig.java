package com.example.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	  @Value("${jwt.cookie}")
	  private String TOKEN_COOKIE;
	
	  @Autowired
	  JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().cors().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests()
		//.antMatchers(HttpMethod.GET, "/collegues").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers("/auth").permitAll()
		.antMatchers("/authentification").permitAll()
		.antMatchers(HttpMethod.POST,"/collegues").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().headers().frameOptions().disable()
		.and()
	    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
	    .logout()
	    .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
	    .deleteCookies(TOKEN_COOKIE); 
	}
	
	@Bean
	   public CorsConfigurationSource corsConfigurationSource() {
	       final CorsConfiguration configuration = new CorsConfiguration();
	       configuration.setAllowedOrigins(Arrays.asList("*"));
	       configuration.setAllowedMethods(Arrays.asList("HEAD",
	               "GET", "POST", "PUT", "DELETE", "PATCH"));
	       // setAllowCredentials(true) is important, otherwise:
	       // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
	       configuration.setAllowCredentials(true);
	       // setAllowedHeaders is important! Without it, OPTIONS preflight request
	       // will fail with 403 Invalid CORS request
	       configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	       final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	       source.registerCorsConfiguration("/**", configuration);
	       return source;
	   }

}
