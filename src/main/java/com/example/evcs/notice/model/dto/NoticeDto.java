package com.example.evcs.notice.model.dto;

import lombok.Builder;

import lombok.Data;

@Data
@Builder
public class NoticeDto {
	private Long id; // ID
    private String eventTitle;
    private String eventContent;
    private String enrollDate; // ISO 형식 문자열 (e.g., 2025-05-01T15:00:00)
    private String status;
    private String writer; 
}
