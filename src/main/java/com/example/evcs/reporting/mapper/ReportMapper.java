package com.example.evcs.reporting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.evcs.reporting.model.vo.Report;

@Mapper
public interface ReportMapper {

    /** 페이징 목록 조회 (Oracle용) */
    @Select("""
        SELECT * FROM (
            SELECT inner_query.*, ROWNUM AS rn
            FROM (
                SELECT
                  b.RP_NO            AS rpNo,
                  b.MEMBER_NO        AS memberNo,
                  b.RP_B_NO          AS boardNo,
                  b.RP_TITLE         AS title,
                  b.RP_MEMBER_NO     AS rpMemberNo,
                  b.RP_CONTENT       AS content,
                  b.RP_ENROLLDATE    AS enrollDate,
                  b.RP_STATUS        AS status,
                  b.RP_END           AS endDate,
                  n.NEWS_NO          AS newsNo,
                  f.RP_NO            AS fileNo,
                  f.FILE_NAME        AS fileName,
                  f.FILE_LINK        AS fileLink,
                  f.FILE_DATE        AS fileDate
                FROM EV_RP_BULLETIN b
                LEFT JOIN EV_RP_NEWS  n ON b.RP_NO = n.RP_NO
                LEFT JOIN EV_RE_FILE  f ON b.RP_NO = f.RP_NO
                WHERE (#{title} IS NULL OR b.RP_TITLE LIKE '%' || #{title} || '%')
                  AND (#{startDate} IS NULL OR b.RP_ENROLLDATE >= TO_DATE(#{startDate}, 'YYYY-MM-DD'))
                  AND (#{endDate} IS NULL OR b.RP_ENROLLDATE <= TO_DATE(#{endDate}, 'YYYY-MM-DD'))
                ORDER BY b.RP_ENROLLDATE DESC
            ) inner_query
            WHERE ROWNUM <= #{offset} + #{size}
        )
        WHERE rn > #{offset}
    """)
    List<Report> selectReportsWithPaging(
        @Param("startDate") String startDate,
        @Param("endDate") String endDate,
        @Param("title") String title,
        @Param("offset") int offset,
        @Param("size") int size
    );

    /** 전체 건수 조회 */
    @Select("""
        SELECT COUNT(*)
        FROM EV_RP_BULLETIN b
        WHERE (#{title} IS NULL OR b.RP_TITLE LIKE '%' || #{title} || '%')
          AND (#{startDate} IS NULL OR b.RP_ENROLLDATE >= TO_DATE(#{startDate}, 'YYYY-MM-DD'))
          AND (#{endDate} IS NULL OR b.RP_ENROLLDATE <= TO_DATE(#{endDate}, 'YYYY-MM-DD'))
    """)
    int countReports(
        @Param("startDate") String startDate,
        @Param("endDate") String endDate,
        @Param("title") String title
    );

    /** 단건 상세 조회 */
    @Select("""
        SELECT
          b.RP_NO            AS rpNo,
          b.MEMBER_NO        AS memberNo,
          b.RP_B_NO          AS boardNo,
          b.RP_TITLE         AS title,
          b.RP_MEMBER_NO     AS rpMemberNo,
          b.RP_CONTENT       AS content,
          b.RP_ENROLLDATE    AS enrollDate,
          b.RP_STATUS        AS status,
          b.RP_END           AS endDate,
          n.NEWS_NO          AS newsNo,
          f.RP_NO            AS fileNo,
          f.FILE_NAME        AS fileName,
          f.FILE_LINK        AS fileLink,
          f.FILE_DATE        AS fileDate
        FROM EV_RP_BULLETIN b
        LEFT JOIN EV_RP_NEWS  n ON b.RP_NO = n.RP_NO
        LEFT JOIN EV_RE_FILE  f ON b.RP_NO = f.RP_NO
        WHERE b.RP_NO = #{rpNo}
    """)
    Report selectReportById(Long rpNo);

    /** 전체 목록 (필요 시 유지) */
    @Select("""
        SELECT
          b.RP_NO            AS rpNo,
          b.MEMBER_NO        AS memberNo,
          b.RP_B_NO          AS boardNo,
          b.RP_TITLE         AS title,
          b.RP_MEMBER_NO     AS rpMemberNo,
          b.RP_CONTENT       AS content,
          b.RP_ENROLLDATE    AS enrollDate,
          b.RP_STATUS        AS status,
          b.RP_END           AS endDate,
          n.NEWS_NO          AS newsNo,
          f.RP_NO            AS fileNo,
          f.FILE_NAME        AS fileName,
          f.FILE_LINK        AS fileLink,
          f.FILE_DATE        AS fileDate
        FROM EV_RP_BULLETIN b
        LEFT JOIN EV_RP_NEWS  n ON b.RP_NO = n.RP_NO
        LEFT JOIN EV_RE_FILE  f ON b.RP_NO = f.RP_NO
        ORDER BY b.RP_ENROLLDATE DESC
    """)
    List<Report> selectAllReports();
    
    /** RP_STATUS 컬럼을 Y → N 으로 업데이트 */
    @Update("""
          UPDATE EV_RP_BULLETIN
          SET RP_STATUS = #{status}
          WHERE RP_NO = #{rpNo}
    """)
    void updateReportStatus(@Param("rpNo") Long rpNo,
                            @Param("status") String status);
    
    @Select("""
    	    SELECT * FROM (
    	        SELECT inner_query.*, ROWNUM AS rn
    	        FROM (
    	            SELECT ...
    	            FROM EV_RP_BULLETIN b
    	            WHERE b.MEMBER_NO = #{memberNo}
    	              AND (#{title} IS NULL OR b.RP_TITLE LIKE '%' || #{title} || '%')
    	              AND (#{startDate} IS NULL OR b.RP_ENROLLDATE >= TO_DATE(#{startDate}, 'YYYY-MM-DD'))
    	              AND (#{endDate} IS NULL OR b.RP_ENROLLDATE <= TO_DATE(#{endDate}, 'YYYY-MM-DD'))
    	            ORDER BY b.RP_ENROLLDATE DESC
    	        ) inner_query
    	        WHERE ROWNUM <= #{offset} + #{size}
    	    )
    	    WHERE rn > #{offset}
    	""")
    List<Report> selectReportsForUser(
    	    @Param("memberNo") Long memberNo,
    	    @Param("startDate") String startDate,
    	    @Param("endDate") String endDate,
    	    @Param("title") String title,
    	    @Param("offset") int offset,
    	    @Param("size") int size
    	);
}