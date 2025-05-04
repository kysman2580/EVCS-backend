package com.example.evcs.admin.carInfo.model.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.evcs.admin.carInfo.model.dao.CarInfoMapper;
import com.example.evcs.admin.carInfo.model.dto.CarImageDTO;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.vo.CarImage;
import com.example.evcs.admin.carInfo.model.vo.CarInfo;
import com.example.evcs.common.file.FileUtil;
import com.example.evcs.exception.DuplicatedCarInfoException;
import com.example.evcs.exception.NonExistingException;
import com.example.evcs.util.model.dto.PageInfo;
import com.example.evcs.util.template.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CarInfoServiceImpl implements CarInfoService{

	private final CarInfoMapper carInfoMapper;
	private FileUtil fileUtil = new FileUtil("uploads/car");
	
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
		
		
		if(file != null && !file.isEmpty()) {
			
			filePath = fileUtil.saveFile(file).replace("uploads/", "uploads/car/");
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
		
		RowBounds rowBounds = new RowBounds((page-1)*carNoPerPage,carNoPerPage);
		List<CarInfoDTO> carInfo = carInfoMapper.findAllCar(rowBounds);
		
		
		map.put("pageInfo", pageInfo);
		map.put("carInfo", carInfo);
		
		return map;
	}

	@Override
	public CarImageDTO getCarImage(String carName) {
		
		CarInfo carInfoData = CarInfo.builder()
				.carName(carName)
				.build();
		
		int result = carInfoMapper.findByCarName(carInfoData);
		
		if(result != 0) {
			log.info("image :{}",carInfoMapper.findImageByCarName(carInfoData));
			log.info("image :{}",carInfoMapper.findImageByCarName(carInfoData));
			return carInfoMapper.findImageByCarName(carInfoData);
		} else {
			throw new NonExistingException("차량이 존재하지 않습니다.");
		}
	}

	@Override
	public void updateCar(CarInfoDTO carInfo, MultipartFile file) {
		
		/*
		 * 1. 차종 번호로 값이 있는지 없는지 조회, 이미지도 있는지 없는지 조회
		 * 
		 * 2. 있다면 수정하러 ㄱㄱ + 이미지도 수정
		 * 
		 * 3. 없다면 수정 못함 => 예외처리
		 */
		
		CarInfo carInfoData = null;
		CarImage carImageData = null;
		String filePath = null;
		
		int result = carInfoMapper.findCarByCarNo(carInfo);
		
		if(result == 1) {
			
			carInfoData = CarInfo.builder()
					.carNo(carInfo.getCarNo())
					.carName(carInfo.getCarName())
					.carType(carInfo.getCarType())
					.carYear(carInfo.getCarYear())
					.carCompany(carInfo.getCarCompany())
					.carBattery(carInfo.getCarBattery())
					.build();
			
			carInfoMapper.updateCar(carInfoData);
		} else {
			throw new NonExistingException("존재하지 않는 차량입니다");
		}
		
		if(file != null && !file.isEmpty()) {
			
			filePath = fileUtil.saveFile(file);
//			filePath = fileUtil.saveFile(file).replace("uploads/", "uploads/car/");
			
			carImageData = CarImage.builder()
							.carNo(carInfo.getCarNo())
							.fileLoad(filePath)
							.build();
			
			int imageResult = carInfoMapper.updateCarImage(carImageData);
			
		} else {
			return;
		}
	}
	
	public void deleteCar(CarInfoDTO carInfo) {
	
		CarInfo carInfoData = null;
		
		int result = carInfoMapper.findCarByCarNo(carInfo);
		
		if(result != 0) {
			
			carInfoData = CarInfo.builder()
					.carNo(carInfo.getCarNo())
					.carName(carInfo.getCarName())
					.carType(carInfo.getCarType())
					.carYear(carInfo.getCarYear())
					.carCompany(carInfo.getCarCompany())
					.carBattery(carInfo.getCarBattery())
					.build();
			
			
			carInfoMapper.deleteCar(carInfoData);
			carInfoMapper.deleteCarImage(carInfo.getCarNo());
		} else {
			throw new NonExistingException("존재하지 않는 차량입니다");
		}
				
		
	}
	
	
	
	
	

	
}
