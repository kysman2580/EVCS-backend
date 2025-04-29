package com.example.evcs.admin.carInfo.model.vo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarImage {

	private int carNo;
	private String fileLoad;
}
