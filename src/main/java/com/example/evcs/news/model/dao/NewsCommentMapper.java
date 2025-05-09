package com.example.evcs.news.model.dao;

import com.example.evcs.news.model.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsCommentMapper {
    void insertComment(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo,
                       @Param("content") String content, @Param("parentId") Long parentId);

    void updateComment(@Param("commentId") Long commentId, @Param("content") String content);

    void softDeleteComment(@Param("commentId") Long commentId);

    List<CommentDTO> findCommentsByNews(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);
    
    @Select("SELECT MEMBER_NO FROM EV_NEWS_CMT WHERE NEWS_CMT_ID = #{commentId}")
    Long findMemberNoByCommentId(@Param("commentId") Long commentId);
}
