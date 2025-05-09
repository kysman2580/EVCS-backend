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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final MemberMapper mapper;
	private final SocialMemberMapper socialMemberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    System.out.println("[UserDetailsServiceImpl] 입력된 username: [" + username + "]");

	    MemberDTO user = mapper.getMemberByEmail(username);
	    System.out.println("[UserDetailsServiceImpl] 일반 회원 조회 결과: " + (user != null ? user.toString() : "null"));

	    // 1. 일반 회원이 존재하면 그대로 처리
	    if (user != null) {
	        return CustomUserDetails.builder()
	                .memberNo(user.getMemberNo())
	                .username(user.getEmail())
	                .password(user.getMemberPw())  // 소셜 회원은 password가 null일 수 있음
	                .memberName(user.getMemberNickname())
	                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole())))
	                .build();
	    }

	    // 2. 일반 회원이 없으면 소셜 회원으로 조회
	    SocialMemberDTO socialMember = socialMemberMapper.findByEmail(username);
	    System.out.println("[UserDetailsServiceImpl] 소셜 회원 조회 결과: " + (socialMember != null ? socialMember.toString() : "null"));

	    if (socialMember != null) {
	        LoginMemberDTO linkedMember = socialMemberMapper.findById(socialMember.getMemberNo());
	        System.out.println("[UserDetailsServiceImpl] 연동된 일반 회원 정보: " + (linkedMember != null ? linkedMember.toString() : "null"));

	        return CustomUserDetails.builder()
	                .memberNo(linkedMember.getMemberNo())
	                .username(linkedMember.getEmail())
	                .password(null)
	                .memberName(linkedMember.getNickname())
	                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + linkedMember.getRole())))
	                .build();
	    }

	    System.out.println("[UserDetailsServiceImpl] 사용자 조회 실패 - 존재하지 않는 사용자");
	    throw new CustomAuthenticationException("존재하지 않는 사용자입니다.");
	}


}
