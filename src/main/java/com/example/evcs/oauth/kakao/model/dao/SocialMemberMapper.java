package com.example.evcs.oauth.kakao.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.oauth.kakao.model.dto.LoginMemberDTO;
import com.example.evcs.oauth.kakao.model.dto.SocialMemberDTO;

@Mapper
public interface SocialMemberMapper {

	void insertSocialMember(SocialMemberDTO socialMember);

	Object getSocialMemberByProviderId(String providerId);

	SocialMemberDTO findByEmail(String username);

	LoginMemberDTO findById(Long memberNo);

}
