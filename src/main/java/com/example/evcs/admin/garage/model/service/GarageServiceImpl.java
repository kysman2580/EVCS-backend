package com.example.evcs.admin.garage.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.evcs.admin.garage.model.dao.GarageMapper;
import com.example.evcs.admin.garage.model.dto.GarageDTO;
import com.example.evcs.admin.garage.model.dto.GarageRegionDTO;
import com.example.evcs.admin.garage.model.vo.Garage;
import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.auth.service.AuthService;
import com.example.evcs.common.board.BoardUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GarageServiceImpl implements GarageService{
	
	private final GarageMapper garageMapper;
	private final AuthService authService;
	private final BoardUtil boardUtil;
	
	@Override
	public void insertGarage(GarageDTO garage) {
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();

		Garage requestData = null;
		
		requestData = Garage.builder()
				.writer(memberNo)
				.allAddress(garage.getAllAddress())
				.regionSido(garage.getRegionSido())
				.regionSigungu(garage.getRegionSigungu())
				.regionDong(garage.getRegionDong())
				.address(garage.getAddress())
				.postAdd(garage.getPostAdd())
				.build();

		garageMapper.insertGarage(requestData);
		
	}

	@Override
	public Map<String, Object> selectGarageAll(Map<String, String> map) {
		
		Map<String, Object> returnMap = new HashMap();
		
		// 특수문자 이스케이프 처리
		map.put("searchKeyword", boardUtil.escapeLikeParam(map.get("searchKeyword")));
		
		List<GarageDTO> list = new ArrayList();
		List<GarageRegionDTO> regionList = new ArrayList();
		
		list = garageMapper.selectGarageAll(map);
		regionList = garageMapper.selectGarageRegion();
		
		log.info("eventList : {}", list);
		log.info("regionList : {}", regionList);
		returnMap.put("eventList", list);
		returnMap.put("regionList", regionList);
		
		return returnMap;
	}

	@Override
	public GarageDTO selectByGarageNo(Long garageNo) {
		return garageMapper.selectByGarageNo(garageNo);
	}

	@Override
	public void updateGarage(GarageDTO garage) {
		
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();

		Garage requestData = null;
		
		requestData = Garage.builder()
				.writer(memberNo)
				.garageNo(garage.getGarageNo())
				.allAddress(garage.getAllAddress())
				.regionSido(garage.getRegionSido())
				.regionSigungu(garage.getRegionSigungu())
				.regionDong(garage.getRegionDong())
				.address(garage.getAddress())
				.postAdd(garage.getPostAdd())
				.build();
		
		garageMapper.updateGarage(requestData);
		
	}

	@Override
	public void deleteByGarageNo(Long eventNo) {
		garageMapper.deleteByGarageNo(eventNo);
		
	}
	

}
