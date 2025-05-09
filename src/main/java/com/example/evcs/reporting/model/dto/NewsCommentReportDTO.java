package com.example.evcs.reporting.model.dto;

import lombok.Data;

@Data
public class NewsCommentReportDTO {
	  private Long newsCmtId;     // 댓글 ID
	  private Long reporter;      // 신고자 회원 번호
	  private String reportReason; // 신고 사유
}
