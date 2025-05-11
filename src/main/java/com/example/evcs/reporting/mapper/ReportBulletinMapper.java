package com.example.evcs.reporting.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.evcs.reporting.model.vo.Report;

@Mapper
public interface ReportBulletinMapper {
	int insertReportBulletin(Report report);
}
