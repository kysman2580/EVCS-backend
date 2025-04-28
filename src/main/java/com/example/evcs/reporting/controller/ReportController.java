package com.example.evcs.reporting.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.reporting.model.vo.Report;
import com.example.evcs.reporting.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
	private final ReportService service;
    public ReportController(ReportService service) {
        this.service = service;
    }

    // 전체 조회
    @GetMapping
    public List<Report> listAll() {
        return service.getAllReports();
    }

    // 단건 조회
    @GetMapping("/{rpNo}")
    public Report getOne(@PathVariable String rpNo) {
        return service.getReport(rpNo);
    }
}
