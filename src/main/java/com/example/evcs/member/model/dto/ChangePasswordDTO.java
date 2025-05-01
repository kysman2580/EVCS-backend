package com.example.evcs.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ChangePasswordDTO {
	
	private String currentPassword;
	private String newPassword;
	private String confirmNewPassword;

}
