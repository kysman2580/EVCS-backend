package com.example.evcs.rentcars.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;

@Mapper
public interface RentCarMapper {
	
	List<RentCarInfoDTO> selectAllRentCar(int category);

}
