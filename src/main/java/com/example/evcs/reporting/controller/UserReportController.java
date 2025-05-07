package com.example.evcs.reporting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.reporting.model.vo.Report;
import com.example.evcs.reporting.service.ReportService;

@RestController 
@RequestMapping("/api/usReports")
public class UserReportController {
	private final ReportService service;

    public UserReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> myReports(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate,
        @RequestParam(required = false) String title
    ) {
        Long memberNo = userDetails.getMemberNo();
        int offset = page * size;

        List<Report> reports = service.getReportsForUser(memberNo, startDate, endDate, title, offset, size);
        int totalCount = service.getTotalReportCountForUser(memberNo, startDate, endDate, title);

        Map<String, Object> result = new HashMap<>();
        result.put("content", reports);
        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
        result.put("totalElements", totalCount);
        result.put("number", page);

        return ResponseEntity.ok(result);
    }
}
