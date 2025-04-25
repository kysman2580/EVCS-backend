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

	  @GetMapping
	  public List<Report> listReports() {
	    return service.getAllReports();
	  }

	  @GetMapping("/{id}")
	  public Report getReport(@PathVariable Long id) {
	    return service.getAllReports()
	                  .stream()
	                  .filter(r -> r.getBoardNo().equals(id))
	                  .findFirst()
	                  .orElseThrow(() -> new RuntimeException("신고를 찾을 수 없습니다."));
	  }
}
