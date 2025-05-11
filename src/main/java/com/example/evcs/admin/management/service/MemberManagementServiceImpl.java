package com.example.evcs.admin.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.evcs.admin.management.dao.MemberManagementMapper;
import com.example.evcs.member.model.vo.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberManagementServiceImpl implements MemberManagementService {

    private final MemberManagementMapper memberManagementMapper;
    
    
    @Override
    public List<Member> getMembersWithPaging(String startDate, String endDate, String email, int offset, int limit) {
        return memberManagementMapper.selectMembersWithPaging(startDate, endDate, email, offset, limit);
    }

    @Override
    public int getTotalMemberCount(String startDate, String endDate, String email) {
        return memberManagementMapper.countMembers(startDate, endDate, email);
    }

    @Transactional
    public boolean updateMemberRole(Long memberNo, String role) {
        int updatedRows = memberManagementMapper.updateMemberRole(memberNo, role);
        return updatedRows > 0;
    }
}