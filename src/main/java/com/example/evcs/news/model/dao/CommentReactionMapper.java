package com.example.evcs.news.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentReactionMapper {

    void insertLike(@Param("newsCmtId") Long newsCmtId, @Param("memberNo") Long memberNo);

    void deleteLike(@Param("newsCmtId") Long newsCmtId, @Param("memberNo") Long memberNo);

    void insertHate(@Param("newsCmtId") Long newsCmtId, @Param("memberNo") Long memberNo);

    void deleteHate(@Param("newsCmtId") Long newsCmtId, @Param("memberNo") Long memberNo);

    int checkLike(@Param("newsCmtId") Long newsCmtId, @Param("memberNo") Long memberNo);

    int checkHate(@Param("newsCmtId") Long newsCmtId, @Param("memberNo") Long memberNo);

    int countLikes(@Param("newsCmtId") Long newsCmtId);

    int countHates(@Param("newsCmtId") Long newsCmtId);
}
