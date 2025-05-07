package com.example.evcs.reporting.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.reporting.model.dto.NewsCommentReportDTO;

@Mapper
public interface NewsCommentReportMapper {
	void insertCommentReport(NewsCommentReportDTO dto);
}
