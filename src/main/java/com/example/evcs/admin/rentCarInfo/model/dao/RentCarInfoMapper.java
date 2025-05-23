package com.example.evcs.admin.rentCarInfo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.example.evcs.admin.carInfo.model.dto.CarCompanyDTO;
import com.example.evcs.admin.carInfo.model.dto.CarInfoDTO;
import com.example.evcs.admin.carInfo.model.dto.CarTypeDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.CategoryDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarInfoDTO;
import com.example.evcs.admin.rentCarInfo.model.dto.RentCarOptionDTO;
import com.example.evcs.admin.rentCarInfo.model.vo.RentCarInfoVO;
import com.example.evcs.admin.rentCarInfo.model.vo.RentCarOption;

@Mapper
public interface RentCarInfoMapper {
 
	// 1. 렌터카 추가하기
	void insertRentCar(RentCarInfoVO rentCarInfo);

	void insertCarOptions(RentCarOption rentCarOption);
	
	List<CategoryDTO> getRentCarCategory();
	List<CarInfoDTO> getAllCarInfo();
	int findByCarNo(int carNo);
	int findCategoryNoByCategoryName(String CategoryName);

	
	// 2. 렌터카 조회하기
	List<RentCarInfoDTO> getRentCarList(Map<String, String> map, RowBounds rowBounds);
	
	int countAllRentCar();
	List<CarInfoDTO> getCarList(RowBounds rowBouds);
	
	// 3. 렌터카 수정하기
	int updateRentCar(RentCarInfoVO rentCarInfoVo);
	
	int findByRentCarNo(String rentCarNo);
	
	
	// 4. 렌터카 삭제하기
	int deleteRentCar(String rentCarNo);



	List<CarCompanyDTO> getCompanyInfo();
	
	List<CarInfoDTO> getCarInfoByCategoryNo();


	List<CarTypeDTO> getCarTypeInfo();
	
	List<RentCarOptionDTO> getOptions();

	List<RentCarOptionDTO> getRentCarOptions(String rentCarNo);
	
	int deleteCarOptions(String rentCarNo);

	List<RentCarInfoDTO> getTimeRentCarInfo(Map<String, Object> dateMap);
	
}
