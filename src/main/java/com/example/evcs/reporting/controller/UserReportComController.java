package com.example.evcs.reporting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.reporting.model.vo.ReComment;
import com.example.evcs.reporting.service.ReCommentService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
@RestController
@RequestMapping("/api/usReportsCom")
public class UserReportComController {
    private final ReCommentService service;

    public UserReportComController(ReCommentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> myReComments(
        @AuthenticationPrincipal CustomUserDetails user,
        @RequestParam(name="page", defaultValue="0") int page,
        @RequestParam(name="size", defaultValue="10") int size,
        @RequestParam(name="startDate", required=false) String startDate,
        @RequestParam(name="endDate",   required=false) String endDate,
        @RequestParam(name="title",   required=false) String title
    ) {
        Long memberNo = user.getMemberNo();
        int offset = page * size;
        List<ReComment> list = service.getReCommentsForUser(memberNo, startDate, endDate, title, offset, size);
        int total = service.getTotalReCommentCountForUser(memberNo, startDate, endDate, title);

        Map<String,Object> result = new HashMap<>();
        result.put("content",       list);
        result.put("totalElements", total);
        result.put("totalPages",    (int)Math.ceil((double)total / size));
        result.put("number",        page);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{reNo}")
    public ResponseEntity<ReComment> detail(@PathVariable("reNo") Long reNo) {
        log.info("detail() called with reNo={}", reNo);

        ReComment c = service.getReCommentById(reNo);
        log.info("service.getReCommentById returned: {}", c);

        if (c == null) {
        	log.warn("No ReComment found for reNo={}", reNo);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }
    
    @PatchMapping("/{rpNo}/o")
    public ResponseEntity<String> cancelReport(
            @PathVariable("rpNo") Long id,
            @RequestBody Map<String, String> body,
            @AuthenticationPrincipal CustomUserDetails user) {

        String status = body.get("reStatus");
        service.updateReportComStatusForUser(id, user.getMemberNo(), status);
        return ResponseEntity.ok("신고 상태가 변경되었습니다.");
    }
}
