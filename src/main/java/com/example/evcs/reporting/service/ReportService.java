// ReportService.java
package com.example.evcs.reporting.service;

import java.util.List;
import com.example.evcs.reporting.model.vo.Report;

public interface ReportService {
	List<Report> getAllReports();

    Report getReportById(Long rpNo);

    List<Report> getReportsWithPaging(String startDate, String endDate, String title, int offset, int size);

    int getTotalReportCount(String startDate, String endDate, String title);
}
