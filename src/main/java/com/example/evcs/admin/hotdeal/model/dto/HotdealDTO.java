package com.example.evcs.admin.hotdeal.model.dto;

import java.sql.Date;
import java.util.List;

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
    private Date startDate;
    private Date endDate;
    private Date enrollDate;
    private String status; 
    private Long writer;
    private String carNo;
    private List<String> carNos;
    private String period;
    private int carCount;
    private String statusName;
	
}
