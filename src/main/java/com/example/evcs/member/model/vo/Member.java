package com.example.evcs.member.model.vo;

import java.sql.Timestamp;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Member {

	private Long memberNo;
	private String email;
	private String memberPw;
	private char emailVerified;
	private String memberNickname;
	private String role;
	private Timestamp createdAt;	
}
