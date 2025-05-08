package com.example.evcs.mail.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.mail.dto.EmailVerifyDTO;
import com.example.evcs.mail.dto.PassWordEmailVerifyDTO;

@Mapper
public interface EmailMapper {

	void updateEmailVerified(EmailVerifyDTO emailVerifyDTO);

	EmailVerifyDTO getEmailVerification(EmailVerifyDTO emailVerifyDTO);


	int existsByEmail(String email);
	
	void updateCode(EmailVerifyDTO emailVerifyDTO);

	void saveCode(EmailVerifyDTO emailVerifyDTO);

	void passwordUpdateCode(PassWordEmailVerifyDTO passWordEmailVerifyDTO);

	void passwordSaveCode(PassWordEmailVerifyDTO passWordEmailVerifyDTO);

	int passwordExistsByEmail(String email);

	EmailVerifyDTO getEmailVerificationByPassword(PassWordEmailVerifyDTO passWordEmailVerifyDTO);

	void updateEmailVerifiedByPassword(PassWordEmailVerifyDTO passWordEmailVerifyDTO);

	



	
	
}
