package com.example.evcs.admin.model.service;

import org.springframework.http.ResponseEntity;

import com.example.evcs.member.model.dto.MemberDTO;

public interface AdminService {
	
	ResponseEntity<String> enterAdmin(MemberDTO member);
}
