package com.example.evcs.admin.carInfo.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.admin.carInfo.model.dto.CarImageDTO;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.vo.CarImage;
import com.example.evcs.admin.carInfo.model.vo.CarInfo;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;

@Mapper
public interface CarInfoMapper {

	int findByCarName(CarInfo carInfoData);
	
	void insertCar(CarInfo carInfoData);
	
	int findCarNoByCarName(CarInfo carInfoData);
	
	void insertCarImage(CarImage carImageData);
	
	int countAllCar();

	List<CarInfoDTO> findAllCar(RowBounds rowBounds);
	
	List<CarInfoDTO> getAllCarInfo();
	
	List<CarImageDTO> getAllCarImage();

	CarImageDTO findImageByCarName(CarInfo carInfoData);

	int findCarByCarNo(CarInfoDTO carInfo);
	
	int updateCar(CarInfo carInfo);
	
	int updateCarImage(CarImage carImageData);
	
	int deleteCar(CarInfo carInfo);
	
	int deleteCarImage(int carNo);

	



	
}
