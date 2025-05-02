package com.example.evcs.admin.rentCarInfo.model.service;

import java.util.List;

import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;


public interface RentCarInfoService {

	public List<String> getRentCarCategory();
	
	public List<CarInfoDTO> getAllCarInfo();
	
	public CarInfoDTO getCarInfo(String carName);
	
	public void insertRentCar(RentCarInfoDTO rentCarInfo);

}
