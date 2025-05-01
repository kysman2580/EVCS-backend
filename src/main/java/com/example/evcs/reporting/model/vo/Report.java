package com.example.evcs.reporting.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    // news
    private Long newsNo;          // EV_RP_NEWS.NEWS_NO (뉴스 ID)

    // drive
    // private Long driveBoardNo;    // EV_RP_DRIVE.BOARD_NO (드라이브 게시판 ID)

    // file
    private Long fileNo;          // EV_RE_FILE.FILE_NO
    private String file;          // EV_RE_FILE.FILE
    private String fileLink;      // EV_RE_FILE.FILE_LINK
    private LocalDateTime fileDate; // EV_RE_FILE.FILE_DATE
}

