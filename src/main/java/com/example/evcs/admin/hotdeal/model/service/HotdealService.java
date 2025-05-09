package com.example.evcs.admin.hotdeal.model.service;

import java.util.Map;

import com.example.evcs.admin.hotdeal.model.dto.HotdealDTO;

public interface HotdealService {

	void insertHotdeal(HotdealDTO hotdeal);
	
	Map<String , Object> selectHotdealAll(Map<String, String> map);
	
	Map<String , Object> selectAllRentCar(Map<String, String> map);

	Map<String , Object> selectAllHotdealRentCar(Map<String, String> map);
	
	void updateHotdeal(HotdealDTO hotdeal);
	
	void deleteByHotdealNo(Long hotdealNo);
}
