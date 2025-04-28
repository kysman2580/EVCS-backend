package com.example.evcs.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.evcs.auth.util.JwtUtil;
import com.example.evcs.token.model.dao.TokenMapper;
import com.example.evcs.token.model.vo.RefreshToken;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;

	@Override
	public Map<String, Object> generateToken(String username, Long memberNo) {

		Map<String, Object> tokens = createToken(username);

		saveToken((String) tokens.get("refreshToken"), memberNo);
		tokenMapper.deleteExpiredRefreshToken(System.currentTimeMillis());

		return tokens;
	}

	private void saveToken(String refreshToken, Long memberNo) {
		RefreshToken token = RefreshToken.builder()
										 .token(refreshToken)
										 .memberNo(memberNo)
										 .expiresAt(System.currentTimeMillis() + 36000000L * 24 * 3)
										 .build();
		
		System.out.println("~~~~~~~~~~~ 몇번오니/???");
		tokenMapper.saveToken(token);
	}

	private Map<String, Object> createToken(String username) {
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);


		Map<String, Object> tokens = new HashMap<>();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);

		return tokens;
	}

	@Override
	public Map<String, Object> refreshToken(String refreshToken) {

        log.info("refreshToken 호출, 전달된 토큰: {}", refreshToken);

		
		RefreshToken token = RefreshToken.builder().token(refreshToken).build();
		RefreshToken responseToken = tokenMapper.findByToken(token);

		if (responseToken == null){
			log.info("토큰이 없습니다잉.");
			throw new RuntimeException("유효하지 않은 토큰입니다.");
		}
		
		if(responseToken.getExpiresAt() < System.currentTimeMillis()) {
			log.info("토큰 만료됨 : {}", responseToken.getExpiresAt());
		}

		String username = getUsernameByToken(refreshToken);
		Long memberNo = responseToken.getMemberNo();

		return generateToken(username, memberNo);
	}
	

	private String getUsernameByToken(String refreshToken) {
		Claims claims = tokenUtil.parseJwt(refreshToken);
		return claims.getSubject();
	}

}
