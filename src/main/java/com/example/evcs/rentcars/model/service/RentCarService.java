package com.example.evcs.rentcars.model.service;

import java.util.List;

import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;

public interface RentCarService {

	List<RentCarInfoDTO> selectAllRentCar(int category);
	
	RentCarInfoDTO selectRentCarByRentCarNo(String rentCarNo);
}
