package com.example.evcs.admin.carInfo.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.admin.carInfo.model.dto.CarImageDTO;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.vo.CarImage;
import com.example.evcs.admin.carInfo.model.vo.CarInfo;

@Mapper
public interface CarInfoMapper {

	int findByCarName(CarInfo carInfoData);
	
	void insertCar(CarInfo carInfoData);
	
	int findCarNoByCarName(CarInfo carInfoData);
	
	void insertCarImage(CarImage carImageData);
	
	int countAllCar();

	List<CarInfoDTO> findAllCar(RowBounds rowBounds);

	CarImageDTO findImageByCarName(CarInfo carInfoData);

	int findCarByCarNo(CarInfoDTO carInfo);
	
	int updateCar(CarInfo carInfo);
	
	int deleteCar(CarInfo carInfo);
	
}
