package com.example.evcs.reporting.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.reporting.model.vo.Report;
import com.example.evcs.reporting.service.ReportService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** [GET] /api/reports — 페이징 목록 조회 */
    @GetMapping
    public ResponseEntity<Map<String, Object>> list(
    		@RequestParam(name = "page", defaultValue = "0") int page,
    	    @RequestParam(name = "size", defaultValue = "10") int size,
    	    @RequestParam(name = "startDate", required = false) String startDate,
    	    @RequestParam(name = "endDate", required = false) String endDate,
    	    @RequestParam(name = "title", required = false) String title
    ) {
        int offset = page * size;
        List<Report> reports = service.getReportsWithPaging(startDate, endDate, title, offset, size);
        int totalCount = service.getTotalReportCount(startDate, endDate, title);

        Map<String, Object> result = new HashMap<>();
        result.put("content", reports);
        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
        result.put("totalElements", totalCount);
        result.put("number", page);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{rpNo}")
    public ResponseEntity<Report> detail(@PathVariable("rpNo") Long rpNo) {
        Report rpt = service.getReportById(rpNo);
        if (rpt == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rpt);
    }
}