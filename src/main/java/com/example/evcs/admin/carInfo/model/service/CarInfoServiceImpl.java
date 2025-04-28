package com.example.evcs.admin.carInfo.model.service;


import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.admin.carInfo.model.dao.CarInfoMapper;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.vo.CarInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarInfoServiceImpl implements CarInfoService{

	private final CarInfoMapper carInfoMapper;
	
	@Override
	public void insertCar(CarInfoDTO carInfo, MultipartFile file) {
		
		CarInfo requestData = null;
		

		
		if(file != null || file.isEmpty()) {
			
			String originalFileName = 
					file.getOriginalFilename();
			
			requestData = CarInfo.builder()
									.carId(carInfo.getCarId())
									.carName(carInfo.getCarName())
									.carType(carInfo.getCarType())
									.carYear(carInfo.getCarYear())
									.carCompany(carInfo.getCarCompany())
									.carBattery(carInfo.getCarBattery())
									.enrollDate(carInfo.getEnrollDate())
									.imageUrl(originalFileName)
									.build();
		}
		carInfoMapper.insertCar(requestData);
		
	}

	
}
