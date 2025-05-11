package com.example.evcs.admin.rentCarInfo.model.vo;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RentCarOption {
	private String rentCarNo;
	private List<Integer> optionNos;
}
