package com.example.evcs.auth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.exception.CustomAuthenticationException;
import com.example.evcs.member.model.dao.MemberMapper;
import com.example.evcs.member.model.dto.MemberDTO;
import com.example.evcs.oauth.kakao.model.dao.SocialMemberMapper;
import com.example.evcs.oauth.kakao.model.dto.LoginMemberDTO;
import com.example.evcs.oauth.kakao.model.dto.SocialMemberDTO;

import ch.qos.logback.classic.pattern.Util;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final MemberMapper mapper;
	private final SocialMemberMapper socialMemberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    System.out.println("[UserDetailsServiceImpl] 입력된 username: [" + username + "]");

	    // 이메일 앞뒤 공백 제거
	    String cleanUsername = username.trim();
	    log.info("[UserDetailsServiceImpl] 정제된 사용자명: {}", cleanUsername);

	    // 1. 일반 회원 조회
	    MemberDTO user = mapper.getMemberByEmail(username);
	    log.info("[UserDetailsServiceImpl] 일반 회원 조회 결과: {}", user);

	    // 2. 일반 회원이 존재하면 그 정보 반환
	    if (user != null) {
	        return CustomUserDetails.builder()
	                .memberNo(user.getMemberNo())
	                .username(user.getEmail())
	                .password(user.getMemberPw())  // 일반 회원의 경우 비밀번호 반환
	                .memberName(user.getMemberNickname())
	                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
	                .build();
	    }

	    // 3. 일반 회원이 없으면 소셜 회원 조회
	    log.info("[UserDetailsServiceImpl] 소셜 회원 조회 시작");
	    SocialMemberDTO socialMember = socialMemberMapper.findByEmail(username);
	    log.info("[UserDetailsServiceImpl] 소셜 회원 조회 결과: {}", socialMember);

	    // 4. 소셜 회원이 존재하면 소셜 로그인 정보 사용
	    if (socialMember != null) {
	        // 소셜 회원의 닉네임을 사용하여 반환
	        return CustomUserDetails.builder()
	                .memberNo(socialMember.getMemberNo())
	                .username(socialMember.getEmail())
	                .password(null)  // 소셜 회원은 비밀번호가 없으므로 null
	                .memberName(socialMember.getNickName())  // 소셜 로그인 시 소셜 닉네임 사용
	                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + socialMember.getRole())))
	                .build();
	    }

	    log.error("[UserDetailsServiceImpl] 사용자 조회 실패 - 존재하지 않는 사용자: {}", cleanUsername);
	    throw new CustomAuthenticationException("존재하지 않는 사용자입니다.");
	}



}
