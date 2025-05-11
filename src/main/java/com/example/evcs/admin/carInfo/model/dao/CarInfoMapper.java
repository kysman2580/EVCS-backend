package com.example.evcs.admin.carInfo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.admin.carInfo.model.dto.CarCompanyDTO;
import com.example.evcs.admin.carInfo.model.dto.CarImageDTO;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.dto.CarTypeDTO;
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
	
	List<CarInfoDTO> getAllCarInfo();
	
	List<CarImageDTO> getAllCarImage();

	CarImageDTO findImageByCarName(CarInfo carInfoData);

	int findCarByCarNo(CarInfoDTO carInfo);
	
	int updateCar(CarInfo carInfo);
	
	int updateCarImage(CarImage carImageData);
	
	int deleteCar(CarInfo carInfo);
	
	int deleteCarImage(int carNo);

	List<CarInfoDTO> selectAllCarInfo(Map<String, String> map);
	
	List<CarCompanyDTO> selectAllCarCompanyInfo();

	List<CarTypeDTO> selectAllCarTypeInfo();


	
}
