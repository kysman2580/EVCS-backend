package com.example.evcs.admin.hotdeal.model.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotdealDTO {

    private Long hotdealNo;
    private String hotdealName;
    private int dealPercent;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime  startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime  endDate;
    private Date enrollDate;
    private String status; 
    private Long writer;
    private String carNo;
    private List<String> carNos;
    private String period;
    private int carCount;
    private String statusName;
	
}
