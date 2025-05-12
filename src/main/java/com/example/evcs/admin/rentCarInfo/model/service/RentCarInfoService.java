package com.example.evcs.admin.rentCarInfo.model.service;

import java.util.List;
import java.util.Map;

import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarOptionDTO;


public interface RentCarInfoService {

	public Map<String,Object> getRentCarCategory();
	
	public Map<String, Object> getAllCarInfo();
	
	
	public void insertRentCar(RentCarInfoDTO rentCarInfo);

	public Map<String, Object> rentCarList(Map<String, String> map);

	public void updateRentCar(RentCarInfoDTO rentCarInfo);

	public void deleteRentCar(RentCarInfoDTO rentCarInfo);

	
	public List<RentCarOptionDTO> getOptions();

	public List<RentCarOptionDTO> getRentCarOptions(String rentCarNo);

	public Map<String, Object> getTimeRentCarInfo(Map<String, Object> dateMap);

}
