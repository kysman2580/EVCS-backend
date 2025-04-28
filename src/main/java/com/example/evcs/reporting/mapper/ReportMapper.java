package com.example.evcs.reporting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.evcs.reporting.model.vo.Report;

@Mapper
public interface ReportMapper {
  @Select(
    "SELECT " +
    " RP_NO         AS rpNo, " +
    " \"Key\"        AS keyField, " +
    " MEMBER_NO     AS memberNo, " +
    " \"Field\"      AS field, " +
    " RP_CONTENT    AS content, " +
    " FILE_NO       AS fileNo, " +
    " RP_ENROLLDATE AS enrollDate, " +
    " RP_STATUS     AS status " +
    "FROM TB_REPORT " +
    "ORDER BY RP_ENROLLDATE DESC"
  )
  List<Report> selectAllReports();

  @Select(
    "SELECT " +
    " RP_NO         AS rpNo, " +
    " \"Key\"        AS keyField, " +
    " MEMBER_NO     AS memberNo, " +
    " \"Field\"      AS field, " +
    " RP_CONTENT    AS content, " +
    " FILE_NO       AS fileNo, " +
    " RP_ENROLLDATE AS enrollDate, " +
    " RP_STATUS     AS status " +
    "FROM TB_REPORT WHERE RP_NO = #{rpNo}"
  )
  Report selectReportById(String rpNo);
}

