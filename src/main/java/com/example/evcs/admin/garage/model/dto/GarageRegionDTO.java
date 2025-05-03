package com.example.evcs.admin.garage.model.dto;

import java.sql.Date;

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
public class GarageRegionDTO {
	private String regionSido;       // 시/도
	private String regionSigungu;    // 시/군/구
	private String regionDong;       // 동
}
