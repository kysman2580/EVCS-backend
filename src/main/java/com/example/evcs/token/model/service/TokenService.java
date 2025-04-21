package com.example.evcs.token.model.service;

import java.util.Map;

public interface TokenService {

	Map<String, String> generateToken(String username, Long memberNo);
	
	
	
	Map<String, String> refreshToken(String refreshToken);
	
}
