package com.example.evcs.token.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshToken {
	private String token;
	private Long memberNo;
	private Long expiration;
}
