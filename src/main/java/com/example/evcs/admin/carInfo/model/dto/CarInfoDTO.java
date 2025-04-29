package com.example.evcs.admin.carInfo.model.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarInfoDTO {
	
	private int carNo;
	private String carName;
	private String carType;
	private int carYear;
	private String carCompany;
	private String carBattery;
	private Date enrollDate;
	
}
