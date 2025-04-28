package com.example.evcs.mail.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PassWordEmailVerifyDTO {
	private String email;
	private String code;
	private Timestamp expiresAt;
	private char verified;	

}
