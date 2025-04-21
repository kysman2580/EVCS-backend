package com.example.evcs.mail.model.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.model.dao.EmailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class EmailServiceImpl implements EmailService {
	
	private final JavaMailSender mailSender;
	private final EmailMapper emailMapper;
	
	

	@Override
	@Transactional
	public void sendVerificationCode(EmailVerifyDTO emailVerifyDTO) {
		String code = generateRandomCode();
		String email = emailVerifyDTO.getEmail();
	    Timestamp expiresAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)); // 30분 유효
	    
	    
	    emailVerifyDTO.setCode(code);
	    emailVerifyDTO.setExpiresAt(expiresAt);
	    emailVerifyDTO.setVerified('N');

		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("[전기충만] 이메일 인증번호 입니다.");
		message.setText(buildEmailBody(code));
		mailSender.send(message);
		emailMapper.saveCode(emailVerifyDTO);
		log.info("ㅎㅇㅎㅇ");
	}
	
	
	public void verifyCode(EmailVerifyDTO emailVerifyDTO) {
		EmailVerifyDTO verifyCode = emailMapper.getEmailVerification(emailVerifyDTO);
		
		log.info("이메일: {}, 인증코드: {}", emailVerifyDTO.getEmail(), emailVerifyDTO.getCode());
		
		if(verifyCode == null || !verifyCode.getCode().equals(emailVerifyDTO.getCode()) ) {
			throw new RuntimeException("인증번호가 유효하지 않습니다.");
		}
		
		emailMapper.updateEmailVerified(emailVerifyDTO);
	}
	
	
	
	
	private String generateRandomCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}
	
	private String buildEmailBody(String code) {
		return String.format("""
				안녕하세요. 전기충만 입니다.
				
				아래 번호를 입력하여 이메일 인증절차 완료해 주세요.
				
				인증번호 : %s
				
				본 인증번호는 30분 후에 만료됩니다.
				
				전기충만 드림
				
				""", code);
	}













}
