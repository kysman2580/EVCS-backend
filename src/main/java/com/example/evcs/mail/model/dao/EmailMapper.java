package com.example.evcs.mail.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.mail.dto.EmailVerifyDTO;

@Mapper
public interface EmailMapper {

	void updateEmailVerified(EmailVerifyDTO emailVerifyDTO);

	EmailVerifyDTO getEmailVerification(EmailVerifyDTO emailVerifyDTO);

	void saveCode(EmailVerifyDTO emailVerifyDTO);

	EmailVerifyDTO findByEmail(EmailVerifyDTO emailVerifyDTO);

	
	
}
