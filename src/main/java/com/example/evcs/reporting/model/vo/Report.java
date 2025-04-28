package com.example.evcs.reporting.model.vo;

import java.time.LocalDate;

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
	private String rpNo;           // RP_NO (PK)
    private String keyField;       // Key 컬럼 (Java reserved word 피해서 이름 변경)
    private Long memberNo;         // MEMBER_NO
    private String field;          // Field
    private String content;        // RP_CONTENT
    private String fileNo;         // FILE_NO
    private LocalDate enrollDate;  // RP_ENROLLDATE
    private char status;           // RP_STATUS
}

