package com.example.evcs.mail.dto;

import lombok.Data;

@Data
public class PasswordUpdateDTO {
	private String email;
	private String newPassword;
	private String confirmPassword;

}
