package com.example.evcs.oauth.kakao.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginMemberDTO {
	private Long memberNo;
	private String email;
	private String nickname;
	private String role;
	private String accessToken;  // 정상적인 로그인 응답에 포함될 accessToken
    private String refreshToken; // 정상적인 로그인 응답에 포함될 refreshToken
}
