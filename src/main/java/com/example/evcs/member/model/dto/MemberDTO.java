package com.example.evcs.member.model.dto;


import java.sql.Timestamp;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class MemberDTO {

	private Long memberNo;
	private String email;
	@Size(min = 4, max = 20, message = "비밀번호 값은 5글자 이상, 20자 이하만 사용할 수 있습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$", message = "비밀번호 값은 영어, 숫자, 특수문자만 사용할 수 있습니다.")
	private String memberPw;
	private String emailVerified;
	private String memberNickname;
	private String role;
	private String memberStatus;
	private Timestamp createdAt;	
}





