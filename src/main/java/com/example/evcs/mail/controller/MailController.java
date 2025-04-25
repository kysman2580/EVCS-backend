package com.example.evcs.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.model.service.EmailService;
import com.example.evcs.member.model.dto.MemberDTO;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private EmailService emailService;

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

}
