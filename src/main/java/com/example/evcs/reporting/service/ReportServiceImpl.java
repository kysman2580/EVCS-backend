// ReportServiceImpl.java
package com.example.evcs.reporting.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.evcs.reporting.mapper.ReportMapper;
import com.example.evcs.reporting.model.vo.Report;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportMapper mapper;

    public ReportServiceImpl(ReportMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Report> getAllReports() {
        return mapper.selectAllReports();
    }

    @Override
    public Report getReportById(Long rpNo) {
        return mapper.selectReportById(rpNo);
    }
}
