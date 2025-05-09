package com.example.evcs.notice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NoticeDto {
	private Long id; // ID
    private String noticeTitle;
    private String noticeWriter; 
    private String noticeContent;
    private String enrollDate; // ISO 형식 문자열 (e.g., 2025-05-01T15:00:00)
    private String status;
}
