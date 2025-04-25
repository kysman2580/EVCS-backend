package com.example.evcs.reporting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.evcs.reporting.model.vo.Report;

@Mapper
public interface ReportMapper {

	@Select("SELECT * FROM reports ORDER BY application_date DESC")
	List<Report> selectAllReports();
	// 추가: 검색, 페이징, 키워드 필터 메소드 선언
}
