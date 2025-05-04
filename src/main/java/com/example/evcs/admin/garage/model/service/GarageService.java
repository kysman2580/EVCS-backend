package com.example.evcs.admin.garage.model.service;

import java.util.Map;

import com.example.evcs.admin.garage.model.dto.GarageDTO;

public interface GarageService {

	void insertGarage(GarageDTO garage);
	
	Map<String , Object> selectGarageAll(Map<String, String> map);
	
	GarageDTO selectByGarageNo(Long garageNo);
	
	void updateGarage(GarageDTO garage);
	
	void deleteByGarageNo(Long garageNo);
	
}
