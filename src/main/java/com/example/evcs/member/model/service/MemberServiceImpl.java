package com.example.evcs.member.model.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.evcs.exception.MemberEmailDuplicationException;
import com.example.evcs.member.model.dao.MemberMapper;
import com.example.evcs.member.model.dto.MemberDTO;
import com.example.evcs.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper mapper;
	private final PasswordEncoder passwordEncoder;

	@Override
    public ResponseEntity<String> signUp(MemberDTO member) {
		MemberDTO searchEmail = mapper.getMemberByEmail(member.getEmail());

		if (searchEmail != null) {
			throw new MemberEmailDuplicationException("이미 존재하는 이메일입니다.");
		}
		
		String emailVerified = mapper.isVerified(member.getEmail());
		
		if(emailVerified == null || "Y".equals(emailVerified)) {
			throw new EmailNotVerifiedException("이메일 인증이 완료되지 않았습니다.");
		}
		
		
		
		Member memberValue = Member.builder()
								   .email(member.getEmail())
								   .memberPw(passwordEncoder.encode(member.getMemberPw()))
								   .emailVerified('Y')
								   .memberNickname(member.getMemberNickname())
								   .role("USER")
								   .build();
		
		
		mapper.signUp(memberValue);
		log.info("{}", member);
		
		return ResponseEntity.ok("이메일 인증 및 회원가입 성공");
	}



	
	
	
	
}
