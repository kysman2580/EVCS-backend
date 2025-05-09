package com.example.evcs.reporting.controller;

import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.reporting.model.dto.NewsCommentReportDTO;
import com.example.evcs.reporting.service.NewsCommentReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class NewsReportController {

    private final NewsCommentReportService commentReportService;

    @PostMapping("/comment")
    public ResponseEntity<?> reportComment(@RequestBody NewsCommentReportDTO dto,
                                           @AuthenticationPrincipal CustomUserDetails user) {
        dto.setReporter(user.getMemberNo());
        commentReportService.reportComment(dto);
        return ResponseEntity.ok("신고가 접수되었습니다.");
    }
}
