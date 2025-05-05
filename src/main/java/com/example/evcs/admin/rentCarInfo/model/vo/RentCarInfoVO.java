package com.example.evcs.admin.rentCarInfo.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RentCarInfoVO {

	private String rentCarNo;
	private int categoryNo;
	private int carNo;
	private int rentCarPrice;
	private Long garageNo;
	private Date enrollDate;
	private String status;
}
