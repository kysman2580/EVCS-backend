package com.example.evcs.news.model.dao;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsReactionMapper {

    int countLikeByNews(long newsNo);

    int countHateByNews(long newsNo);

    int hasLiked(Long newsNo, Long memberNo);

    void insertLike(Long newsNo, Long memberNo);

    void deleteLike(Long newsNo, Long memberNo);

    int hasHated(Long newsNo, Long memberNo);

    void insertHate(Long newsNo, Long memberNo);

    void deleteHate(Long newsNo, Long memberNo);

    void insertComment(Long newsNo, Long memberNo, String content);

    int countUserComments(Long newsNo, Long memberNo);

    int countBookmarks(Long newsNo, Long memberNo);

    void insertBookmark(Long newsNo, Long memberNo);

    void deleteBookmark(Long newsNo, Long memberNo);
}

