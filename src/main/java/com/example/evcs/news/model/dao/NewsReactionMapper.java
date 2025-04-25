package com.example.evcs.news.model.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NewsReactionMapper {

	    int countLikeByNews(@Param("newsNo") long newsNo);
	    
	    int countHateByNews(@Param("newsNo") long newsNo);
	    
	    int hasLiked(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    void insertLike(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    void deleteLike(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    int hasHated(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    void insertHate(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    void deleteHate(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    void insertComment(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo, @Param("content") String content);

	    int countUserComments(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    void insertBookmark(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);

	    void deleteBookmark(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);
	    
	    int countBookmarks(@Param("newsNo") Long newsNo, @Param("memberNo") Long memberNo);
}

