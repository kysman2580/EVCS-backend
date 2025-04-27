package com.example.evcs.admin.carInfo.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.admin.carInfo.model.vo.CarInfo;

@Mapper
public interface CarInfoMapper {

	
	void insertCar(CarInfo carInfo);
}
