package com.example.evcs.mail.model.service;

import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.member.model.dto.MemberDTO;

public interface EmailService {


	void verifyCode(EmailVerifyDTO emailVerifyDTO);

	void sendVerificationCode(EmailVerifyDTO emailVerifyDTO);
	
}
