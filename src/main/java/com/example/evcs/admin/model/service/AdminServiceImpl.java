package com.example.evcs.admin.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.evcs.admin.model.dao.AdminMapper;
import com.example.evcs.exception.NotAdminRoleException;
import com.example.evcs.member.model.dao.MemberMapper;
import com.example.evcs.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
	
    private final MemberMapper memberMapper;

	
	@Override
	public void banMember(Long memberNo) {

		int updated = memberMapper.updateMemberStatus(memberNo, "N");
		if (updated == 0) {
			throw new IllegalArgumentException("해당 회원을 찾을 수 없습니다.");
		}

	}

}
