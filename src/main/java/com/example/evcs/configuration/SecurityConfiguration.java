package com.example.evcs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.evcs.configuration.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
	
	private final JwtFilter filter;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable)
						   .httpBasic(AbstractHttpConfigurer::disable)
						   .csrf(AbstractHttpConfigurer::disable)
						   .authorizeHttpRequests(requests -> {
							   requests.requestMatchers(HttpMethod.POST, "/**").permitAll();
							   requests.requestMatchers(HttpMethod.GET, "/**").permitAll();
						   })
						   .sessionManagement(manager ->
						   manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						   .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
						   .build();
	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
