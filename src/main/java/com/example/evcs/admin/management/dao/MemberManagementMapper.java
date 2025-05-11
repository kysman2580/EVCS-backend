package com.example.evcs.admin.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.evcs.member.model.vo.Member;

@Mapper
public interface MemberManagementMapper {
    
    /**
     * 회원 목록을 페이징하여 조회
     * 
     * @param startDate 시작 날짜 (가입일 기준)
     * @param endDate 종료 날짜 (가입일 기준)
     * @param email 이메일 검색어
     * @param offset 페이지 시작점
     * @param limit 한 페이지당 항목 수
     * @return 회원 목록
     */
    List<Member> selectMembersWithPaging(
            @Param("startDate") String startDate, 
            @Param("endDate") String endDate, 
            @Param("email") String email, 
            @Param("offset") int offset, 
            @Param("limit") int limit);
    
    /**
     * 검색 조건에 맞는 총 회원 수 조회
     * 
     * @param startDate 시작 날짜 (가입일 기준)
     * @param endDate 종료 날짜 (가입일 기준)
     * @param email 이메일 검색어
     * @return 총 회원 수
     */
    int countMembers(
            @Param("startDate") String startDate, 
            @Param("endDate") String endDate, 
            @Param("email") String email);
    
    
    int updateMemberRole(@Param("memberNo") Long memberNo, @Param("role") String role);
    
}