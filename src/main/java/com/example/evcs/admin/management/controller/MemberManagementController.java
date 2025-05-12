package com.example.evcs.admin.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.admin.management.service.MemberManagementService;
import com.example.evcs.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/management")
public class MemberManagementController {

    private final MemberManagementService memberManagementService;

    /** [GET] /api/admin/management/members — 회원 목록 페이징 조회 */
    @GetMapping("/members")
    public ResponseEntity<Map<String, Object>> listMembers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "email", required = false) String email) {
        
        int offset = page * size;
        List<Member> members = memberManagementService.getMembersWithPaging(startDate, endDate, email, offset, size);
        int totalCount = memberManagementService.getTotalMemberCount(startDate, endDate, email);

        Map<String, Object> result = new HashMap<>();
        result.put("content", members);
        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
        result.put("totalElements", totalCount);
        result.put("number", page);

        return ResponseEntity.ok(result);
    }
    
    
    @PutMapping("/{memberNo}/role")
    public ResponseEntity<?> updateMemberRole(
            @PathVariable("memberNo") Long memberNo,  // 명시적으로 파라미터 이름 지정
            @RequestBody Map<String, String> payload) {
        String role = payload.get("role");

        if (role == null || (!role.equals("ADMIN") && !role.equals("USER"))) {
            return ResponseEntity.badRequest().body("Invalid role. Role must be either 'ADMIN' or 'USER'");
        }

        boolean updated = memberManagementService.updateMemberRole(memberNo, role);

        if (updated) {
            return ResponseEntity.ok().body(Map.of("message", "Role updated successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}