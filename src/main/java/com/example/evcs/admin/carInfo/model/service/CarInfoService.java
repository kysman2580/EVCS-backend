package com.example.evcs.admin.carInfo.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;

public interface CarInfoService {

	void insertCar(CarInfoDTO carInfo, MultipartFile file);
}
