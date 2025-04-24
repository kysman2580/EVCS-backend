package com.example.evcs.auth.service;

import java.util.Map;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.member.model.dto.MemberDTO;

public interface AuthService {
	
	Map<String, String> login(MemberDTO member);
	
	CustomUserDetails getUserDetails();

}
