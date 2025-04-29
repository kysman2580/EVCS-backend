package com.example.evcs.admin.carInfo.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarInfo {

	private int carNo;
	private String carName;
	private String carType;
	private int carYear;
	private String carCompany;
	private String carBattery;
	private Date enrollDate;
}
