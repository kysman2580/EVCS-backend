package com.example.evcs.mail.model.service;

import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.dto.PassWordEmailVerifyDTO;

public interface EmailSenderService {
	
	void sendVerificationCode(EmailVerifyDTO emailVerifyDTO);
	
	void sendPassWordVerificationCode(PassWordEmailVerifyDTO passWordEmailVerifyDTO);

	void resendVerificationCode(EmailVerifyDTO email);


}
