package com.example.evcs.admin.renCarInfo.model.dto;

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
	private int categoryNo;
	private int carNo;
	private int rentCarPrice;
	private String enroll_place;
	private Date enroll_date;
	private String status;
}
