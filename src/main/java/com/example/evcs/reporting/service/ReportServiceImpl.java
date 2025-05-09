package com.example.evcs.reporting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.evcs.reporting.model.dao.ReportDao;
import com.example.evcs.reporting.model.vo.Report;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
	
	private final ReportDao reportDao;

    @Autowired
    public ReportServiceImpl(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public void updateReportStatus(Long rpNo, String status) {
        Map<String, Object> map = new HashMap<>();
        map.put("rpNo", rpNo);
        map.put("status", status);
        reportDao.updateReportStatus(map);
    }

    @Override
    public List<Report> getAllReports() {
        return reportDao.selectAllReports();
    }

    @Override
    public Report getReportById(Long rpNo) {
    	Report rv = reportDao.selectReportById(rpNo);
    	
    	log.info("뭐니너? : {}", rv);
        return rv;
    }

    @Override
    public List<Report> getReportsWithPaging(String startDate, String endDate, String title, int offset, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("title", title);
        map.put("offset", offset);
        map.put("size", size);
        return reportDao.selectReportsWithPaging(map);
    }

    @Override
    public int getTotalReportCount(String startDate, String endDate, String title) {
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("title", title);
        return reportDao.countReports(map);
    }

    @Override
    public List<Report> getReportsForUser(Long memberNo, String startDate, String endDate, String title, int offset, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", memberNo);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("title", title);
        map.put("offset", offset);
        map.put("size", size);
        return reportDao.selectReportsForUser(map);
    }

    @Override
    public int getTotalReportCountForUser(Long memberNo, String startDate, String endDate, String title) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberNo", memberNo);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("title", title);
        return reportDao.countReportsForUser(map);
    }
}