package com.example.evcs.admin.rentCarInfo.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.vo.RentCarInfoVO;

@Mapper
public interface RentCarInfoMapper {
 
	// 1. 렌터카 추가하기
	void insertRentCar(RentCarInfoVO rentCarInfo);
	
	List<String> getRentCarCategory();
	List<CarInfoDTO> getAllCarInfo();
	CarInfoDTO getCarInfo(String carName);
	
	int findCarNoByCarName(String carName);
	
	int findByCarNo(String carNo);
	
	// 2. 렌터카 조회하기
	List<RentCarInfoDTO> findRentCar();
	
	// 3. 렌터카 수정하기
	int updateRentCar();
	
	
	// 4. 렌터카 삭제하기
	int deleteRentCar();



	


}
