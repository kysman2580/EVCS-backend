package com.example.evcs.token.model.service;

import java.util.Map;

public interface TokenService {

	Map<String, Object> generateToken(String username, Long memberNo);
	
	
	
	Map<String, Object> refreshToken(String refreshToken);






	
}
