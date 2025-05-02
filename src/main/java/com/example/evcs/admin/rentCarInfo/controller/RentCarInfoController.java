package com.example.evcs.admin.rentCarInfo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.service.RentCarInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/rentCar")
@RestController
@Slf4j
@RequiredArgsConstructor
public class RentCarInfoController {

	private final RentCarInfoService rentCarInfoService;
	
	@GetMapping("/category")
	public ResponseEntity<?> getRentCarCategory(){
		
		List<String> category = rentCarInfoService.getRentCarCategory();
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@GetMapping("/carInfo")
	public ResponseEntity<?> getAllCarInfo(){
		log.info("값이 들어오나요???");
		Map<String, Object> allCarInfo = rentCarInfoService.getAllCarInfo();
		return ResponseEntity.status(HttpStatus.CREATED).body(allCarInfo);
	}
	
	@GetMapping("/{carName}")
	public ResponseEntity<?> getCarInfo(@PathVariable(name="carName") String carName) {
		
		CarInfoDTO carInfo = rentCarInfoService.getCarInfo(carName);
		return ResponseEntity.status(HttpStatus.CREATED).body(carInfo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
