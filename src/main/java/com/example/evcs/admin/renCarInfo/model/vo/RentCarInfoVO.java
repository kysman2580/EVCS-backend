package com.example.evcs.admin.renCarInfo.model.vo;

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
	private String enroll_place;
	private Date enroll_date;
	private String status;
}
