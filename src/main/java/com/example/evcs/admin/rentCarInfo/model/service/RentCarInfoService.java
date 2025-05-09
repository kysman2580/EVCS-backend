package com.example.evcs.admin.rentCarInfo.model.service;

import java.util.List;
import java.util.Map;

import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;


public interface RentCarInfoService {

	public List<String> getRentCarCategory();
	
	public Map<String, Object> getAllCarInfo();
	
	
	public void insertRentCar(RentCarInfoDTO rentCarInfo);

	public Map<String, Object> rentCarList(Map<String, String> map);

	public void updateRentCar(RentCarInfoDTO rentCarInfo);

	public void deleteRentCar(RentCarInfoDTO rentCarInfo);

	public Map<String, Object> getTimeRentCarInfo();

}
