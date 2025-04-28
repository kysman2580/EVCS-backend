package com.example.evcs.admin.carInfo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.service.CarInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
@Slf4j
public class CarInfoController {
	
	private final CarInfoService carInfoService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertCar(CarInfoDTO carInfo,
									   @RequestParam(name="imageUrl") MultipartFile file){
		carInfoService.insertCar(carInfo,file);
		
		return null;
	}



}
