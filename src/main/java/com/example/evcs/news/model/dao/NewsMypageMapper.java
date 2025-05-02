package com.example.evcs.news.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.evcs.news.model.dto.NewsDTO;

@Mapper
public interface NewsMypageMapper {
    List<NewsDTO> selectLikedNews(@Param("memberNo") Long memberNo);
    List<NewsDTO> selectBookmarkedNews(@Param("memberNo") Long memberNo);
}