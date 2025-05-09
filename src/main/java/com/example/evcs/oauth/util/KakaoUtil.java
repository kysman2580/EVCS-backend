package com.example.evcs.oauth.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.evcs.exception.AuthHandler;
import com.example.evcs.oauth.kakao.model.dto.KakaoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KakaoUtil {
	
	@Value("${kakao.auth.client}")
	private String client;
	
	@Value("${kakao.auth.redirect}")
	private String redirect;
	
	
	public KakaoDTO.OAuthToken requestToken(String accessCode) {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "authorization_code");
	    params.add("client_id", client);
	    params.add("redirect_uri", redirect);
	    params.add("code", accessCode);

	    // 만들어 놓은 body와 header를 연결하여 준다!
	    HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

	    try {
	        ResponseEntity<String> response = restTemplate.exchange(
	            "https://kauth.kakao.com/oauth/token",
	            HttpMethod.POST,
	            kakaoTokenRequest,
	            String.class);

	        // 상태 코드가 200 OK가 아닌 경우 예외 처리
	        if (response.getStatusCodeValue() != 200) {
	            throw new AuthHandler("카카오 토큰 요청 실패, 상태 코드: " + response.getStatusCodeValue());
	        }

	        ObjectMapper objectMapper = new ObjectMapper();
	        KakaoDTO.OAuthToken oAuthToken = objectMapper.readValue(response.getBody(), KakaoDTO.OAuthToken.class);
	        log.info("oAuthToken : {}", oAuthToken.getAccess_token());

	        return oAuthToken;
	    } catch (Exception e) {
	        throw new AuthHandler("액세스 토큰 요청 중 오류 발생: " + e.getMessage());
	    }
	}

	
	
	
	public KakaoDTO.KakaoProfile requestProfile(KakaoDTO.OAuthToken oAuthToken) {
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	    headers.add("Authorization", "Bearer " + oAuthToken.getAccess_token());

	    HttpEntity<MultiValueMap<String, String>> kakaoProfileRequestEntity = new HttpEntity<>(headers);

	    try {
	        ResponseEntity<String> response = restTemplate.exchange(
	                "https://kapi.kakao.com/v2/user/me",
	                HttpMethod.GET,
	                kakaoProfileRequestEntity,
	                String.class);

	        // 상태 코드가 200 OK가 아닌 경우 예외 처리
	        if (response.getStatusCodeValue() != 200) {
	            throw new AuthHandler("카카오 프로필 요청 실패, 상태 코드: " + response.getStatusCodeValue());
	        }

	        ObjectMapper objectMapper = new ObjectMapper();
	        KakaoDTO.KakaoProfile kakaoProfile = objectMapper.readValue(response.getBody(), KakaoDTO.KakaoProfile.class);
	        log.info("kakaoProfile : {}", kakaoProfile);

	        return kakaoProfile;
	    } catch (Exception e) {
	        throw new AuthHandler("카카오 프로필 요청 중 오류 발생: " + e.getMessage());
	    }
	}

	

}
