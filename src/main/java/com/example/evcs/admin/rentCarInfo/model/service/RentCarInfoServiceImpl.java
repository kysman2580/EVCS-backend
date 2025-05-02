package com.example.evcs.admin.rentCarInfo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.evcs.admin.carInfo.model.dao.CarInfoMapper;
import com.example.evcs.admin.carInfo.model.dto.CarImageDTO;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.dao.RentCarInfoMapper;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class RentCarInfoServiceImpl implements RentCarInfoService {

	private final RentCarInfoMapper rentCarInfoMapper;
	private final CarInfoMapper carInfoMapper;
	
	@Override
	public List<String> getRentCarCategory() {
		List<String> result =  rentCarInfoMapper.getRentCarCategory();
		log.info("aa : {}", result);
		return result;
		
	}
	

	@Override
	public Map<String, Object> getAllCarInfo() {
		
		Map<String, Object> result = new HashMap();
		
		List<CarInfoDTO> carInfoResult = carInfoMapper.getAllCarInfo();
		List<CarImageDTO> imageResult = carInfoMapper.getAllCarImage();
		
		result.put("carInfoResult", carInfoResult);
		result.put("imageResult",imageResult);
		
		log.info("123123123123 : {}",imageResult);
		return result;
	}



	@Override
	public CarInfoDTO getCarInfo(String carName) {
		rentCarInfoMapper.getCarInfo(carName);
		return null;
	}

	
	
	
	
	@Override
	public void insertRentCar(RentCarInfoDTO rentCarInfo) {
		
		/*
		 * 1. 모델명으로 carNo조회하기
		 * 
		 * 2. carNo으로 존재하는 렌트카인지 확인
		 * 
		 * 3. 존재한다면 렌터카 추가하기
		 * 
		 * 4. 존재하지 않는다면 예외처리
		 */
		
//		String carName = rentCarInfo.getRentCarNo()
//		rentCarInfoMapper.findCarNoByCarName();
//		
//		
//		rentCarInfoMapper.findByCarNo();
//		
//		
//		rentCarInfoMapper.insertRentCar();
		
		
		
		
		
		
		
	}










	
	
	
	
}
