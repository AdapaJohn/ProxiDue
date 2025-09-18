package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicSecurityAuthentication {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return 
				http
				.authorizeHttpRequests(
				auth -> 
				auth
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf().disable()
				.build();
	}
}

//1.By creating method all security disabled
//2.Access to localhost was denied by adding http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
//3.pop up signin box by adding http.httpBasic(Customizer.withDefaults());
//4.csrf disabled 