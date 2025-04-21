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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberDTO user = mapper.getMemberByEmail(username);
		if(user == null) {
			throw new CustomAuthenticationException("존재하지 않는 사용자입니다.");
		}
		
		return CustomUserDetails.builder()
				.memberNo(user.getMemberNo())
				.username(user.getEmail())
				.password(user.getMemberPw())
				.memberName(user.getMemberNickname())
				.authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
				.build();
	}
	
	
	
	
	
	
	
	
	
	

}
