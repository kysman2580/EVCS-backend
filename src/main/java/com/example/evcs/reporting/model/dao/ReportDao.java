package com.example.evcs.reporting.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.evcs.reporting.model.vo.Report;

@Repository
public class ReportDao {

	private final SqlSession sqlSession;

    public ReportDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void updateReportStatus(Map<String, Object> paramMap) {
        sqlSession.update("report-mapper.updateReportStatus", paramMap);
    }

    public List<Report> selectAllReports() {
        return sqlSession.selectList("report-mapper.selectAllReports");
    }

    public Report selectReportById(Long rpNo) {
        return sqlSession.selectOne("report-mapper.selectReportById", rpNo);
    }

    public List<Report> selectReportsWithPaging(Map<String, Object> paramMap) {
        return sqlSession.selectList("report-mapper.selectReportsWithPaging", paramMap);
    }

    public int countReports(Map<String, Object> paramMap) {
        return sqlSession.selectOne("report-mapper.countReports", paramMap);
    }

    public List<Report> selectReportsForUser(Map<String, Object> paramMap) {
        return sqlSession.selectList("report-mapper.selectReportsForUser", paramMap);
    }

    public int countReportsForUser(Map<String, Object> paramMap) {
        return sqlSession.selectOne("report-mapper.countReportsForUser", paramMap);
    }
}