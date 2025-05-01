package com.example.evcs.reporting.model.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NewsReportRequestDTO {
	private Long memberNo;       // 신고자
    private String title;        // RP_TITLE
    private Long rpMemberNo;     // 피신고자
    private String content;      // RP_CONTENT
    private LocalDateTime enrollDate; // RP_ENROLLDATE
    private Long newsNo;         // EV_RP_NEWS.NEWS_NO
}
