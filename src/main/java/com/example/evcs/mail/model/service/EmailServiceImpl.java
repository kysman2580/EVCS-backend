package com.example.evcs.mail.model.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.evcs.exception.EmailNotVerifiedException;
import com.example.evcs.exception.InvalidVerificationCodeException;
import com.example.evcs.exception.VerificationCodeMismatchException;
import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.dto.PassWordEmailVerifyDTO;
import com.example.evcs.mail.model.dao.EmailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class EmailServiceImpl implements EmailService {

	private final EmailMapper emailMapper;

	public void verifyCode(EmailVerifyDTO emailVerifyDTO) {
		EmailVerifyDTO verifyCode = emailMapper.getEmailVerification(emailVerifyDTO);

		if (verifyCode == null || !verifyCode.getCode().equals(emailVerifyDTO.getCode())) {
			throw new EmailNotVerifiedException("인증번호가 유효하지 않습니다.");
		}

		emailMapper.updateEmailVerified(emailVerifyDTO);
	}

	@Override
	public void passwordVerifyCode(PassWordEmailVerifyDTO passWordEmailVerifyDTO) {
		EmailVerifyDTO verifyCode = emailMapper.getEmailVerificationByPassword(passWordEmailVerifyDTO);

		if (passWordEmailVerifyDTO.getCode() == null || passWordEmailVerifyDTO.getCode().trim().isEmpty()) {
			throw new InvalidVerificationCodeException("인증번호를 입력해주세요.");
		}

		if (!verifyCode.getCode().equals(passWordEmailVerifyDTO.getCode())) {
			throw new VerificationCodeMismatchException("입력한 인증번호가 일치하지 않습니다.");
		}

		emailMapper.updateEmailVerifiedByPassword(passWordEmailVerifyDTO);
	}

}
