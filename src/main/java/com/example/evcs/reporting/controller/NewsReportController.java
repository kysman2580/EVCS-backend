package com.example.evcs.reporting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.evcs.reporting.model.dto.NewsCommentReportDTO;
import com.example.evcs.reporting.service.NewsCommentReportService;

  @RestController
  @RequiredArgsConstructor
  @RequestMapping("/api/report")
public class NewsReportController {

    private final NewsCommentReportService commentReportService;

    @PostMapping("/comment")
    public ResponseEntity<?> reportComment(@RequestBody NewsCommentReportDTO dto) {
        commentReportService.reportComment(dto);
        return ResponseEntity.ok("신고가 접수되었습니다.");
    }
}