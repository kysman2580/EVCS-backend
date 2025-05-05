package com.example.evcs.admin.garage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.admin.garage.model.dto.GarageDTO;
import com.example.evcs.admin.garage.model.dto.GarageRegionDTO;
import com.example.evcs.admin.garage.model.vo.Garage;

@Mapper
public interface GarageMapper {

	void insertGarage(Garage garage);
	
	List<GarageDTO> selectGarageAll(Map<String, String> map);
	
	List<GarageRegionDTO> selectGarageRegion();
	
	GarageDTO selectByGarageNo(Long garageNo);
	
	void updateGarage(Garage garage);
	
	void deleteByGarageNo(Long garageNo);
	
}
