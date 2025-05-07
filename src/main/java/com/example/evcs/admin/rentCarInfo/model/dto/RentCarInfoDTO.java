package com.example.evcs.admin.rentCarInfo.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RentCarInfoDTO {

	private String rentCarNo;
	private String categoryName;
	private int carNo;
	private int rentCarPrice;
	private Long garageNo;
	private Date enrollDate;
	private String status;
	private String enrollPlace;
	private String postAdd;
	private String statusName;
	private String fileLoad;
	private String carCompany;
	private String carType;
	private String carName;
	private String carYear;
	private String hotdealNo;
}
