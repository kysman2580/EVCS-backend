package com.example.evcs.rentcars.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;
import com.example.evcs.rentcars.model.dao.RentCarMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentCarServiceImpl implements RentCarService{

	private final RentCarMapper rentCarMapper;
	
	@Override
	public List<RentCarInfoDTO> selectAllRentCar(int category) {
		
		List<RentCarInfoDTO> list = rentCarMapper.selectAllRentCar(category);
		
		return list;
	}

}
