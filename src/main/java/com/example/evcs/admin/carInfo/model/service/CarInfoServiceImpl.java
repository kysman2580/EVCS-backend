package com.example.evcs.admin.carInfo.model.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.admin.carInfo.file.FileService;
import com.example.evcs.admin.carInfo.model.dao.CarInfoMapper;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.vo.CarImage;
import com.example.evcs.admin.carInfo.model.vo.CarInfo;
import com.example.evcs.exception.DuplicatedCarInfoException;
import com.example.evcs.util.model.dto.PageInfo;
import com.example.evcs.util.template.Pagination;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarInfoServiceImpl implements CarInfoService{

	private final CarInfoMapper carInfoMapper;
	private final FileService fileService;
	
	@Override
	public void insertCar(CarInfoDTO carInfo, MultipartFile file) {
		
		/* 1. 차종명으로 db에 이미 존재하는 차종명인지를 확인
		 * 
		 * 2. 존재하지 않는다면 차정보,image insert하기
		 * 	  
		 * 3. 존재한다면 예외처리
		 */
		
		
		
		CarInfo carInfoData = null;
		CarImage carImageData = null;
		String filePath = null;
		
		if(file != null || file.isEmpty()) {
			
			filePath = fileService.save(file);
			
			carInfoData = CarInfo.builder()
									.carName(carInfo.getCarName())
									.carType(carInfo.getCarType())
									.carYear(carInfo.getCarYear())
									.carCompany(carInfo.getCarCompany())
									.carBattery(carInfo.getCarBattery())
									.build();
		}
		
		// 1. 차종명으로 존재하는지 찾기
		int result = carInfoMapper.findByCarName(carInfoData);
		
		if(result == 0) { // 2. 존재하지 않는다면 db에 추가
			
			carInfoMapper.insertCar(carInfoData);
			int carNo = carInfoMapper.findCarNoByCarName(carInfoData);
			carImageData = CarImage.builder()
					.carNo(carNo)
					.fileLoad(filePath)
					.build();
			carInfoMapper.insertCarImage(carImageData);
		} else {  // 3. 존재한다면 예외처리
			throw new DuplicatedCarInfoException("이미 존재하는 차량 입니다");
		}
		
		
						
		
	}

	@Override
	public Map<String, Object> carList(int page) {
		
		Map<String, Object> map = new HashMap(); 
		
		int carNoPerPage = 10;
		int pageSize = 5;
		int totalCarNo = carInfoMapper.countAllCar();
		
		PageInfo pageInfo = Pagination.getPageInfo(page, pageSize, carNoPerPage, totalCarNo);
		
		RowBounds rowBounds = new RowBounds(page*carNoPerPage,carNoPerPage);
		List<CarInfoDTO> carInfo = carInfoMapper.findAllCar(rowBounds);
		
		map.put("pageInfo", pageInfo);
		map.put("carInfo", carInfo);
		
		return map;
	}
	
	
	
	
	

	
}
