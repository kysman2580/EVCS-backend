package com.example.evcs.auth.service;

import java.util.Map;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.member.model.dto.MemberDTO;
import com.example.evcs.oauth.kakao.model.dto.LoginMemberDTO;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

	Map<String, Object> login(MemberDTO member);

	CustomUserDetails getUserDetails();

}
