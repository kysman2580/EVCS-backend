package com.example.evcs.oauth.kakao.model.service;

import com.example.evcs.oauth.kakao.model.dto.LoginMemberDTO;

import jakarta.servlet.http.HttpServletResponse;

public interface OAuthService {
	LoginMemberDTO oAuthLogin(String accessCode, HttpServletResponse httpServletResponse);
}
