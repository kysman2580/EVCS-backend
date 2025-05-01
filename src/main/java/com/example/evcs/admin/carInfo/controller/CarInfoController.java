package com.example.evcs.admin.carInfo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.admin.carInfo.model.dto.CarImageDTO;
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
	
	// 차종 추가
	@PostMapping("/insert")
	public ResponseEntity<?> insertCar(CarInfoDTO carInfo,
									   @RequestParam(name="image") MultipartFile file){
		
		System.out.println("aaaaa");
		log.info("carInfo : {},file : {}",carInfo,file);
		carInfoService.insertCar(carInfo,file);
		
		return ResponseEntity.status(HttpStatus.OK).body("차량 등록에 성공하셨습니다~");
	}
	
	// 차종 조회
	@GetMapping("/{currentPage}")
	public ResponseEntity<?> carList(@PathVariable(name="currentPage") int currentPage){
		
		log.info("{}",currentPage);
		Map<String, Object> map = carInfoService.carList(currentPage);
		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}
	
	@GetMapping("/image/{name}")
	public ResponseEntity<?> getCarImage(@PathVariable(name="name") String carName){
		
		log.info("carName : {}",carName);
		CarImageDTO carImage = carInfoService.getCarImage(carName);
		return ResponseEntity.status(HttpStatus.CREATED).body(carImage);
	}
	
	
	// 차종 수정
	@PostMapping("/update")
	public ResponseEntity<?> updateCar(CarInfoDTO carInfo,
										@RequestParam(name="image") MultipartFile file) {
		
		carInfoService.updateCar(carInfo, file);
		
		return ResponseEntity.status(HttpStatus.OK).body("차량정보가 수정되었습니다.");
	}
	
	// 차종 삭제
	@PostMapping("/delete")
	public ResponseEntity<?> updateCar(CarInfoDTO carInfo) {
		
		carInfoService.deleteCar(carInfo);
		
		return ResponseEntity.status(HttpStatus.OK).body("차량이 삭제되었습니다.");
	}
}


















