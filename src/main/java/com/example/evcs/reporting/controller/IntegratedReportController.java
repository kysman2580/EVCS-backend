package com.example.evcs.reporting.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.reporting.model.vo.Report;
import com.example.evcs.reporting.service.ReportService;

import lombok.Data;

@RestController
@RequestMapping("/api/integrated-reports")
public class IntegratedReportController {

    private final ReportService reportService;

    public IntegratedReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Void> submitIntegratedReport(
            @RequestBody IntegratedReportRequest req
    ) {
        // VO로 매핑
    	Report r = new Report();
        r.setReporterId(req.getReporterId());
        r.setBoardNo(req.getBoardId());
        r.setTitle(req.getBoardTitle());
        r.setReportedId(req.getReportedId());
        r.setContent(req.getContent());
        r.setEnrollDate(LocalDateTime.now());
        r.setStatus("P");

        reportService.createReport(r);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 요청 바디용 DTO
    @Data
    public static class IntegratedReportRequest {
        private Long reporterId;   // 로그인한 신고자 ID
        private Long boardId;      // 이전 페이지에서 받은 게시글 번호
        private String boardTitle; // 이전 페이지에서 받은 게시글 제목
        private Long reportedId;   // 이전 페이지에서 받은 신고 대상자 ID
        private String content;    // 유저가 입력한 신고 사유
        // (파일 첨부는 별도 처리 필요)
    }
}