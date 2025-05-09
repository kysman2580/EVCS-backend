package com.example.evcs.admin.hotdeal.model.vo;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Hotdeal {
	private Long hotdealNo;
    private String hotdealName;
    private int dealPercent;
    private Date startDate;
    private Date endDate;
    private Long writer;
    private List<String> carNos;
}
