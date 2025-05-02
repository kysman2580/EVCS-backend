package com.example.evcs.admin.carInfo.model.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.admin.carInfo.model.dto.CarImageDTO;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;

public interface CarInfoService {

	void insertCar(CarInfoDTO carInfo, MultipartFile file);
	
	Map<String, Object> carList(int page);

	CarImageDTO getCarImage(String carName);

	void updateCar(CarInfoDTO carInfo, MultipartFile file);

	void deleteCar(CarInfoDTO carInfo);
}
