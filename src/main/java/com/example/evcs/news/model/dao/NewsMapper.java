package com.example.evcs.news.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.evcs.news.model.dto.NewsDTO;

@Mapper
public interface NewsMapper {

     NewsDTO findByTitleAndUrl(@Param("title") String title, @Param("originUrl") String originUrl);
     
     void insert(NewsDTO dto);
     
     void imageInsert(@Param("newsNo") Long newsNo, @Param("imageUrl") String imageUrl);
     
     Long getLastInsertId();
     
     
     void incrementWatchCount(@Param("newsNo") Long newsNo);

	NewsDTO findByNewsNo(@Param("newsNo")Long newsNo);


}