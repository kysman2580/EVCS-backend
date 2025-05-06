package com.example.evcs.reporting.service;

import org.springframework.stereotype.Service;

import com.example.evcs.reporting.mapper.NewsCommentReportMapper;
import com.example.evcs.reporting.model.dto.NewsCommentReportDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsCommentReportServiceImpl implements NewsCommentReportService {
	 private final NewsCommentReportMapper NewscommentReportMapper;

	    @Override
	    @Transactional
	    public void reportComment(NewsCommentReportDTO dto) {
	    	NewscommentReportMapper.insertCommentReport(dto);
	    }
}
