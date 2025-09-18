package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JwtSecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
//        
//        // h2-console is a servlet 
//        // https://github.com/spring-projects/spring-security/issues/12310
//    	return httpSecurity
//                .authorizeHttpRequests(auth -> auth
//                    .antMatchers("/authenticate").permitAll()
//                    .requestMatchers(PathRequest.toH2Console()).permitAll() // h2-console is a servlet and NOT recommended for a production
//                    .antMatchers(HttpMethod.OPTIONS,"/**")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated())
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.
//                    sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(
//                        OAuth2ResourceServerConfigurer::jwt)
//                //.httpBasic(Customizer.withDefaults())
//                .headers(header -> {header.
//                    frameOptions().sameOrigin();})
//                .build();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        return http
        		.authorizeHttpRequests(auth -> auth
        			    .requestMatchers(new MvcRequestMatcher(introspector, "/authenticate")).permitAll()
        			    .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
        			    .requestMatchers(new AntPathRequestMatcher("/**", "OPTIONS")).permitAll()
        			    .requestMatchers(new AntPathRequestMatcher("/api/orders/**")).hasRole("USER")
        			    .anyRequest().authenticated())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .oauth2ResourceServer(oauth2 -> 
            oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
        )
            //.httpBasic(Customizer.withDefaults())      //sign-in dialog box
            .headers(headers -> headers.frameOptions().sameOrigin())
            .build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix(""); // No prefix
        converter.setAuthoritiesClaimName("scope");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("nanna")
                                .password("{noop}amma")
                                .authorities("read")
                                .roles("USER")
                                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        JWKSet jwkSet = new JWKSet(rsaKey());
        return (((jwkSelector, securityContext) 
                        -> jwkSelector.select(jwkSet)));
    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey().toRSAPublicKey())
                .build();
    }
    
    @Bean
    public RSAKey rsaKey() {
        
        KeyPair keyPair = keyPair();
        
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Unable to generate an RSA Key Pair", e);
        }
    }
    
}

