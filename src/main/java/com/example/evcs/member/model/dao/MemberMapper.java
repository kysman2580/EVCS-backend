package com.example.evcs.member.model.dao;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.evcs.member.model.dto.MemberDTO;
import com.example.evcs.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	int signUp(Member member);

	MemberDTO getMemberByEmail(String email);
	
	String getEmailVerificationStatus(String email);
	
	String isVerified(String email);

	@Select("SELECT VERIFICATION_CODE FROM EMAIL_VERIFICATION WHERE EMAIL = #{email} AND EXPIRY_DATE > CURRENT_TIMESTAMP")
	String getVerificationCode(String email);

	@Delete("DELETE FROM EMAIL_VERIFICATION WHERE EMAIL = #{email}")
	void deleteVerificationCode(String email);

	@Update("UPDATE EL_MEMBER SET EMAIL_VERIFIED = 'Y' WHERE EMAIL = #{email}")
	void updateEmailVerified(String email);
	
	
}
