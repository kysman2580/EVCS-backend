package com.example.evcs.configuration.filter;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{


	private final UserDetailsService userService;
	private final JwtUtil util;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String token = authorization.substring(7);
		
		log.info("userService class: {}", userService.getClass());
		
		try {
		
		Claims claims = util.parseJwt(token);
		String username = claims.getSubject();
		
		CustomUserDetails user = (CustomUserDetails)userService.loadUserByUsername(username);
		
		UsernamePasswordAuthenticationToken authentication
		= new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		} catch (ExpiredJwtException e) {
			log.info("만료됨");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("만료된 토큰입니다.");
			return;
		} catch (JwtException e) {
			log.info("요효하지 않은 토큰값");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("만료된 토큰입니다.");
			return;
		}
		filterChain.doFilter(request, response);
	}
}
