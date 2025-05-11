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
@RequestMapping("/api/amReportsCom")
public class ReportComController {
	private final ReCommentService service;

    public ReportComController(ReCommentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> myReComments(
        @RequestParam(name="page", defaultValue="0") int page,
        @RequestParam(name="size", defaultValue="10") int size,
        @RequestParam(name="startDate", required=false) String startDate,
        @RequestParam(name="endDate",   required=false) String endDate,
        @RequestParam(name="title",   required=false) String title
    ) {
        int offset = page * size;
        List<ReComment> list = service.getReCommentsForAdim(startDate, endDate, title, offset, size);
        int total = service.getTotalReCommentCountForAdim(startDate, endDate, title);

        Map<String,Object> result = new HashMap<>();
        result.put("content",       list);
        result.put("totalElements", total);
        result.put("totalPages",    (int)Math.ceil((double)total / size));
        result.put("number",        page);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{reNo}")
    public ResponseEntity<ReComment> detail(@PathVariable("reNo") Long reNo) {
        ReComment c = service.getReCommentById(reNo);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }
}