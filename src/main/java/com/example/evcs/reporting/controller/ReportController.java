package com.example.evcs.reporting.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.evcs.reporting.service.ReportService;
import com.example.evcs.reporting.model.vo.Report;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    /** [GET] /api/reports — 전체 목록 */
    @GetMapping
    public ResponseEntity<List<Report>> list() {
        List<Report> reports = service.getAllReports();
        return ResponseEntity.ok(reports);
    }

    /** [GET] /api/reports/{rpNo} — 상세 조회 */
    @GetMapping("/{rpNo}")
    public ResponseEntity<Report> detail(@PathVariable Long rpNo) {
        Report rpt = service.getReportById(rpNo);
        if (rpt == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rpt);
    }
}
