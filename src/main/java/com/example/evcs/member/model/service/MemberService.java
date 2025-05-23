package com.example.evcs.member.model.service;

import org.springframework.http.ResponseEntity;

import com.example.evcs.member.model.dto.ChangePasswordDTO;
import com.example.evcs.member.model.dto.MemberDTO;

public interface MemberService {
	
	ResponseEntity<String> signUp(MemberDTO member);

	void updatePassword(String email, String newPassword, String confirmNewPassword);
	
	void changePassword(ChangePasswordDTO changePasswordDTO, String email);


	

	
	
	  


}
