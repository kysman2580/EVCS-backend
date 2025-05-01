package com.example.evcs.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.evcs.exception.EmailNotFoundException;
import com.example.evcs.exception.EmailNotVerifiedException;
import com.example.evcs.exception.MemberEmailDuplicationException;
import com.example.evcs.exception.PasswordMismatchException;
import com.example.evcs.member.model.dao.MemberMapper;
import com.example.evcs.member.model.dto.ChangePasswordDTO;
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

		if (emailVerified == null || !"Y".equals(emailVerified)) {
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

	@Override
	public void updatePassword(String email, String newPassword) {
		String encodedPassword = passwordEncoder.encode(newPassword);
		log.info("Encoded Password: {}", encodedPassword);

		String emailVerified = mapper.isVerifiedByPassword(email);

		if (emailVerified == null || !"Y".equals(emailVerified)) {
			throw new EmailNotVerifiedException("이메일 인증이 완료되지 않았습니다.");
		}

		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		params.put("encodedPassword", encodedPassword);

		mapper.updatePassword(params);
	}
	
	

	@Override
	public void changePassword(ChangePasswordDTO changePasswordDTO, String email) {
		MemberDTO member = mapper.getMemberByEmail(email);

		
	    if (changePasswordDTO.getNewPassword() == null || changePasswordDTO.getNewPassword().trim().isEmpty()) {
	        throw new PasswordMismatchException("새 비밀번호를 입력해주세요.");
	    }

		if(changePasswordDTO.getNewPassword() == null || changePasswordDTO.getConfirmNewPassword() == null) {
			throw new PasswordMismatchException("새 비밀번호와 비밀번호 확인란을 모두 입력해주세요");
		}
		
		
		if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
			throw new PasswordMismatchException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다");
		}
		
		
		if(changePasswordDTO.getNewPassword().equals(member.getMemberPw())) {
			throw new PasswordMismatchException("기존 비밀번호와 새 비밀번호는 다르게 입력해주세요.");
		}
		
		/*
		if(passwordEncoder.matches(changePasswordDTO.getNewPassword(), member.getMemberPw())) {
		    throw new PasswordMismatchException("기존 비밀번호와 새 비밀번호는 다르게 입력해주세요.");
		}
		이거 적용해야함 내일 확인하기
		*/  
		
		
		if(!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), member.getMemberPw())){
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}	
		
		member.setMemberPw(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
		mapper.changePassword(member);
		
	}



}
