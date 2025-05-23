package com.example.evcs.admin.rentCarInfo.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentCarOptionDTO {

	private int optionNo;
	private String optionName;
	private List<Integer> options;
}
