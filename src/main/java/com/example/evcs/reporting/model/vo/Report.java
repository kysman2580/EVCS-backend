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
	private Long boardNo;
    private String title;
    private String reporter;
    private String defendant;
    private LocalDate applicationDate;
    private String status;
}
