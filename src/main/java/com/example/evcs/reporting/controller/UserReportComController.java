package com.example.evcs.reporting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.reporting.model.vo.ReComment;
import com.example.evcs.reporting.service.ReCommentService;

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
        @RequestParam(name="keyword",   required=false) String keyword
    ) {
        Long memberNo = user.getMemberNo();
        int offset = page * size;
        List<ReComment> list = service.getReCommentsForUser(memberNo, startDate, endDate, keyword, offset, size);
        int total = service.getTotalReCommentCountForUser(memberNo, startDate, endDate, keyword);

        Map<String,Object> result = new HashMap<>();
        result.put("content",       list);
        result.put("totalElements", total);
        result.put("totalPages",    (int)Math.ceil((double)total / size));
        result.put("number",        page);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{reNo}")
    public ResponseEntity<ReComment> detail(@PathVariable Long reNo) {
        ReComment c = service.getReCommentById(reNo);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }
}
