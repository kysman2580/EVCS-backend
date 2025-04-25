package com.example.evcs.admin.model.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.admin.model.service.AdminService;
import com.example.evcs.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("admin")
@RestController
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	
	
	@PostMapping
	public ResponseEntity<?> EnterAdmin(@RequestBody MemberDTO member){
		
		
		return ResponseEntity.ok("관리자 페이지로 이동!");
	}

}
