package com.example.evcs.reporting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.evcs.reporting.model.vo.Report;

@Mapper
public interface ReportMapper {

  /** 전체 신고 목록 조회 (최신순) */
	@Select(
		    "SELECT " +
		    " b.RP_NO            AS rpNo, " +
		    " b.MEMBER_NO        AS memberNo, " +
		    " b.RP_B_NO          AS boardNo, " +
		    " b.RP_TITLE         AS title, " +
		    " b.RP_MEMBER_NO     AS rpMemberNo, " +
		    " b.RP_CONTENT       AS content, " +
		    " b.RP_ENROLLDATE    AS enrollDate, " +
		    " b.RP_STATUS        AS status, " +
		    " b.RP_END           AS endDate, " +
		    " n.NEWS_NO          AS newsNo, " +
		    " f.RP_NO            AS fileNo, " +
		    " f.FILE_NAME        AS fileName, " +
		    " f.FILE_LINK        AS fileLink, " +
		    " f.FILE_DATE        AS fileDate " +
		    "FROM EV_RP_BULLETIN b " +
		    "LEFT JOIN EV_RP_NEWS  n ON b.RP_NO = n.RP_NO " +
		    "ORDER BY b.RP_ENROLLDATE DESC"
		  )
		  List<Report> selectAllReports();

		  /** 단일 신고 상세 조회 (드라이브 JOIN 제거) */
	@Select(
		    "SELECT " +
		    " b.RP_NO            AS rpNo, " +
		    " b.MEMBER_NO        AS memberNo, " +
		    " b.RP_B_NO          AS boardNo, " +
		    " b.RP_TITLE         AS title, " +
		    " b.RP_MEMBER_NO     AS rpMemberNo, " +
		    " b.RP_CONTENT       AS content, " +
		    " b.RP_ENROLLDATE    AS enrollDate, " +
		    " b.RP_STATUS        AS status, " +
		    " b.RP_END           AS endDate, " +
		    " n.NEWS_NO          AS newsNo, " +
		    " f.RP_NO            AS fileNo, " +
		    " f.FILE_NAME        AS fileName, " +
		    " f.FILE_LINK        AS fileLink, " +
		    " f.FILE_DATE        AS fileDate " +
		    "FROM EV_RP_BULLETIN b " +
		    "LEFT JOIN EV_RP_NEWS  n ON b.RP_NO = n.RP_NO " +
		    "LEFT JOIN EV_RE_FILE  f ON b.RP_NO = f.RP_NO " +
		    "WHERE b.RP_NO = #{rpNo}"
		  )
		  Report selectReportById(Long rpNo);

}
