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
    		@AuthenticationPrincipal CustomUserDetails user,
    	    @RequestParam(name = "page", defaultValue = "0") int page,
    	    @RequestParam(name = "size", defaultValue = "10") int size,
    	    @RequestParam(name = "startDate", required = false) String startDate,
    	    @RequestParam(name = "endDate", required = false) String endDate,
    	    @RequestParam(name = "title", required = false) String title
    ) {
    	System.out.println("userDetails = " + user);
        Long memberNo = user.getMemberNo();
        System.out.println("memberNo = " + memberNo);
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
    
    @GetMapping("/{rpNo}")
    public ResponseEntity<Report> detail(@PathVariable("rpNo") Long rpNo) {
        Report rpt = service.getReportById(rpNo);
        if (rpt == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rpt);
    }
    
//    @GetMapping("/{rpNo}/o")
//    public ResponseEntity<Report> detail(@PathVariable("rpNo") Long rpNo) {
//        Report rpt = service.getReportById(rpNo);
//        if (rpt == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(rpt);
//    }
}
