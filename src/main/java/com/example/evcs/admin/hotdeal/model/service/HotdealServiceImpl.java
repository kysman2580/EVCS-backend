package com.example.evcs.admin.hotdeal.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.example.evcs.admin.hotdeal.model.dao.HotdealMapper;
import com.example.evcs.admin.hotdeal.model.dto.HotdealDTO;
import com.example.evcs.admin.hotdeal.model.vo.Hotdeal;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;
import com.example.evcs.auth.model.vo.CustomUserDetails;
import com.example.evcs.auth.service.AuthService;
import com.example.evcs.common.board.BoardUtil;
import com.example.evcs.common.board.PageInfo;
import com.example.evcs.common.board.Pagination;
import com.example.evcs.exception.DuplicateHotdealException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotdealServiceImpl implements HotdealService {

	private final HotdealMapper hotdealMapper;
	private final AuthService authService;
	private final BoardUtil boardUtil;
	
	@Override
	public void insertHotdeal(HotdealDTO hotdeal) {
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();
		
		Hotdeal requestData = null;
		
		requestData = Hotdeal.builder()
				.writer(memberNo)
				.hotdealName(hotdeal.getHotdealName())
				.dealPercent(hotdeal.getDealPercent())
				.startDate(hotdeal.getStartDate())
				.endDate(hotdeal.getEndDate())
				.carNos(hotdeal.getCarNos())
				.build();
		
		// 입력한 기간에 이미 핫딜이 진행중인 렌트카가 있는지 체크
		List<String> conflict = hotdealMapper.selectDuplicateHotdeal(requestData);
	    if (!conflict.isEmpty()) {
	        throw new DuplicateHotdealException(conflict);
	    }

		hotdealMapper.insertHotdeal(requestData);
		hotdealMapper.insertIngHotdeal(requestData);
	}

	@Override
	public Map<String, Object> selectHotdealAll(Map<String, String> map) {
		int size = 10;
		Map<String, Object> returnMap = new HashMap();
		
		int count = hotdealMapper.selectTotalCount(map);
		
		RowBounds rowBounds = new RowBounds(Integer.parseInt(map.get("page")) * size, size);
		
		PageInfo pi = Pagination.getPageInfo(count, Integer.parseInt(map.get("page")) +1, 10, 5);
		
		// 특수문자 이스케이프 처리
		map.put("searchKeyword", boardUtil.escapeLikeParam(map.get("searchKeyword")));
		
		List<HotdealDTO> list = new ArrayList();
		
		list = hotdealMapper.selectHotdealAll(map);
		
		log.info("hotdealList : {}", list);
		log.info("pageInfo : {}", pi);
		returnMap.put("hotdealList", list);
		returnMap.put("pageInfo", pi);
		
		return returnMap;
	}
	
	@Override
	public Map<String, Object> selectAllRentCar(Map<String, String> map) {
		Map<String, Object> returnMap = new HashMap();
		
		// 특수문자 이스케이프 처리
		map.put("searchKeyword", boardUtil.escapeLikeParam(map.get("searchKeyword")));
		
		List<RentCarInfoDTO> list = new ArrayList();
		
		list = hotdealMapper.selectAllRentCar(map);
		
		log.info("rentCarList : {}", list);
		returnMap.put("rentCarList", list);
		
		return returnMap;
	}

	@Override
	public void updateHotdeal(HotdealDTO hotdeal) {
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();

		Hotdeal requestData = null;
		
		requestData = Hotdeal.builder()
				.writer(memberNo)
				.hotdealNo(hotdeal.getHotdealNo())
				.hotdealName(hotdeal.getHotdealName())
				.dealPercent(hotdeal.getDealPercent())
				.startDate(hotdeal.getStartDate())
				.endDate(hotdeal.getEndDate())
				.build();

		hotdealMapper.updateHotdeal(requestData);
	}

	@Override
	public void deleteByHotdealNo(Long hotdealNo) {
		hotdealMapper.deleteByHotdealNo(hotdealNo);
	}

}
