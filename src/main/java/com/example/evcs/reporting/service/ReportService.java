package com.example.evcs.reporting.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.evcs.reporting.model.vo.Report;


public interface ReportService {
	void updateReportStatus(Long rpNo, String status);
    
    List<Report> getAllReports();

    Report getReportById(Long rpNo);

    List<Report> getReportsWithPaging(String startDate, String endDate, String title, int offset, int size);

    int getTotalReportCount(String startDate, String endDate, String title);

	List<Report> getReportsForUser(Long memberNo, String startDate, String endDate, String title, int offset, int size);

	int getTotalReportCountForUser(Long memberNo, String startDate, String endDate, String title);
	
	void createReport(Report report);
	
	void updateReportStatusForUser(Long rpNo, Long memberNo, String status);
	
}
