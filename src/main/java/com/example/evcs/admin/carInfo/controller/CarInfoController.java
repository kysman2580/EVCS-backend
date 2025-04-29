package com.example.evcs.admin.carInfo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
									   @RequestParam(name="image") MultipartFile file){
		
		System.out.println("aaaaa");
		log.info("carInfo : {},file : {}",carInfo,file);
		carInfoService.insertCar(carInfo,file);
		
		return ResponseEntity.status(HttpStatus.OK).body("차량 등록에 성공하셨습니다~");
	}
	
	@GetMapping
	public ResponseEntity<?> carList(@RequestParam(name="page",defaultValue="1" ) int page){
		
		Map<String, Object> map = carInfoService.carList(page);
		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}
}
