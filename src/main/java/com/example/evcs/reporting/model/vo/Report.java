package com.example.evcs.reporting.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Report {
    // bulletin
    private Long rpNo;            // RP_NO
    private Long memberNo;        // MEMBER_NO
    private Long boardNo;         // RP_B_NO (뉴스신고면 0)
    private String title;         // RP_TITLE
    private Long rpMemberNo;      // RP_MEMBER_NO (피신고자)
    private String content;       // RP_CONTENT
    private LocalDateTime enrollDate; // RP_ENROLLDATE
    private String status;        // RP_STATUS
    private LocalDate endDate;    // RP_END (nullable)
    private Long newsNo;          // EV_RP_NEWS.NEWS_NO (뉴스 ID)
    private Long fileNo;          // EV_RE_FILE.FILE_NO
    private String file;          // EV_RE_FILE.FILE
    private String fileLink;      // EV_RE_FILE.FILE_LINK
    private LocalDateTime fileDate; // EV_RE_FILE.FILE_DATE
    private Long reporterId;     // MEMBER_NO (신고자)
    private Long reportedId;     // RP_MEMBER_NO (피신고자)
}

