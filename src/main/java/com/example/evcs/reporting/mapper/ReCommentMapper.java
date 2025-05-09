package com.example.evcs.reporting.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.evcs.reporting.model.vo.ReComment;

@Mapper
public interface ReCommentMapper {
    List<ReComment> selectReCommentsForUser(
        @Param("memberNo") Long memberNo,
        @Param("startDate") String startDate,
        @Param("endDate")   String endDate,
        @Param("keyword")   String keyword,
        @Param("offset")    int offset,
        @Param("size")      int size
    );

    int countReCommentsForUser(
        @Param("memberNo") Long memberNo,
        @Param("startDate") String startDate,
        @Param("endDate")   String endDate,
        @Param("keyword")   String keyword
    );

    ReComment selectReCommentById(@Param("reNo") Long reNo);
}
