package com.example.evcs.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.member.model.dto.MemberDTO;
import com.example.evcs.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("members")
@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	
	@PostMapping
	public ResponseEntity<?> SignUp(@RequestBody @Valid MemberDTO member){
		memberService.signUp(member);
		
		return ResponseEntity.status(201).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
