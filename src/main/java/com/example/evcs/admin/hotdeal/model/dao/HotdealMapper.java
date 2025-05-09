package com.example.evcs.admin.hotdeal.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.admin.hotdeal.model.dto.HotdealDTO;
import com.example.evcs.admin.hotdeal.model.vo.Hotdeal;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;

@Mapper
public interface HotdealMapper {
	void insertHotdeal(Hotdeal hotdeal);

	void insertIngHotdeal(Hotdeal hotdeal);
	
	List<String> selectDuplicateHotdeal(Hotdeal hodeal);
	
	int selectTotalCount(Map<String, String> map);
	
	List<HotdealDTO> selectHotdealAll(Map<String, String> map);

	List<RentCarInfoDTO> selectAllRentCar(Map<String, String> map);

	List<RentCarInfoDTO> selectAllHotdealRentCar(Map<String, String> map);
	
	void deleteHotdealCars(Long hotdealNo);
	
	void updateInsertIngHotdeal(Hotdeal hotdeal);

	void updateHotdeal(Hotdeal hotdeal);
	
	void deleteByHotdealNo(Long hotdealNo);
}
