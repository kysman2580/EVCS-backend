package com.example.evcs.admin.renCarInfo.model.service;

import org.springframework.stereotype.Service;

import com.example.evcs.admin.renCarInfo.model.dao.RentCarInfoMapper;
import com.example.evcs.admin.renCarInfo.model.dto.RentCarInfoDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RentCarInfoServiceImpl implements RentCarInfoService {

	private final RentCarInfoMapper rentCarInfoMapper;
	
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
		
		String carName = rentCarInfo.getRentCarNo()
		rentCarInfoMapper.findCarNoByCarName();
		
		
		rentCarInfoMapper.findByCarNo();
		
		
		rentCarInfoMapper.insertRentCar();
		
		
		
		
		
		
		
	}

	
	
	
	
}
