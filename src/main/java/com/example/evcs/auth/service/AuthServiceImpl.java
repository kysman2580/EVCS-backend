package com.example.evcs.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.exception.CustomAuthenticationException;
import com.example.evcs.member.model.dto.MemberDTO;
import com.example.evcs.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
	
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	

	@Override
	public Map<String, Object> login(MemberDTO member) {
		Authentication authentication = null;
		
		try {
			authentication = authenticationManager.authenticate
					(new UsernamePasswordAuthenticationToken(member.getEmail(),
															 member.getMemberPw()));
		} catch (AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
		}
						
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();

		log.info("인증에 성공한 사용자의 정보 : {}", user);
		
		Map<String, Object> loginResponse = tokenService.generateToken(user.getUsername(), 
																	   user.getMemberNo());
		
		loginResponse.put("email", user.getUsername());
		loginResponse.put("memberName", user.getMemberName());
		loginResponse.put("memberNo", user.getMemberNo());
		
		return loginResponse;
	}

		@Override
		public CustomUserDetails getUserDetails() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object principal = auth.getPrincipal();
			System.out.println("Principal type: " + principal.getClass().getName());
			System.out.println("Principal value: " + principal);
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			return user;
		}


}
