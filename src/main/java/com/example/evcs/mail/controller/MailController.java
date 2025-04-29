/*
package com.example.evcs.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.dto.PassWordEmailVerifyDTO;
import com.example.evcs.mail.dto.PasswordUpdateDTO;
import com.example.evcs.mail.model.service.EmailService;
import com.example.evcs.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

	private EmailService emailService;
	@Autowired
	private MemberService memberService;

	@PostMapping("/send")
	public ResponseEntity<String> sendVerificationCode(@RequestBody EmailVerifyDTO email) {
		emailService.sendVerificationCode(email);
		log.info("이메일 : {}", email);
		return ResponseEntity.ok("인증번호 전송 완료");
	}

	@PostMapping("/verify")
	public ResponseEntity<String> verifyCode(@RequestBody EmailVerifyDTO emailVerifyDTO) {
		try {
			emailService.verifyCode(emailVerifyDTO);
			return ResponseEntity.ok("인증 성공");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패");
		}
	}
	
	
	@PostMapping("/password-reset")
	public ResponseEntity<String> sendVerificationCode(@RequestBody PassWordEmailVerifyDTO passWordEmailVerifyDTO) {
		emailService.sendPassWordVerificationCode(passWordEmailVerifyDTO);
		log.info("이메일 : {}", passWordEmailVerifyDTO);
		return ResponseEntity.ok("인증번호 전송 완료");
	}
	
	@PostMapping("/password-verify")
	public ResponseEntity<String> verifyCode(@RequestBody PassWordEmailVerifyDTO passWordEmailVerifyDTO) {
		try {
			emailService.passwordVerifyCode(passWordEmailVerifyDTO);
			return ResponseEntity.ok("인증 성공");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패");
		}
	}
	
	@PostMapping("/password/update")
	public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateDTO passwordUpdateDTO){
		if(!passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getConfirmPassword())) {
			return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
		}	
		log.info(passwordUpdateDTO.getEmail(), passwordUpdateDTO.getNewPassword());
		memberService.updatePassword(passwordUpdateDTO.getEmail(), passwordUpdateDTO.getNewPassword());
		return ResponseEntity.ok("비밀번호 변경 완료.");
	}
	
	
	

}
*/