package com.example.evcs.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.exception.CustomAuthenticationException;
import com.example.evcs.member.model.dao.MemberMapper;
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
	private final MemberMapper memberMapper;

	@Override
	public Map<String, Object> login(MemberDTO member) {
	    Authentication authentication;

	    try {
	        authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(member.getEmail(), member.getMemberPw())
	        );
	    } catch (AuthenticationException e) {
	        // 로그인 실패 시 이메일 존재 여부와 관계없이 동일한 메시지 반환
	        throw new CustomAuthenticationException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
	    }

	    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
	    log.info("인증에 성공한 사용자의 정보 : {}", user);

	    // 인증 후 계정 상태 확인 (UserDetails에서 가져옴)
	    if ("N".equals(user.getMemberStatus())) {
	        throw new CustomAuthenticationException("계정이 정지되었습니다.");
	    }

	    if ("R".equals(user.getMemberStatus())) {
	        throw new CustomAuthenticationException("계정이 비활성화되었습니다.");
	    }

	    Map<String, Object> loginResponse = tokenService.generateToken(user.getUsername(), user.getMemberNo());
	    loginResponse.put("email", user.getUsername());
	    loginResponse.put("memberName", user.getMemberName());
	    loginResponse.put("memberNo", user.getMemberNo());

	    return loginResponse;
	}


	@Override
	public CustomUserDetails getUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		return user;
	}

	@Override
	@Transactional
	public void deleteAccount() {
		log.info("deleteAccount() 메서드 호출됨"); // 여기도 확인
		CustomUserDetails member = getUserDetails();
		log.info("member: {}", member); // 찍히는지 확인

		int result = memberMapper.updateMemberStatusToRemoved(member.getMemberNo());
		log.info("update 결과: {}", result);
	}
}
