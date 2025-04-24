package com.example.evcs.news.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.evcs.news.model.dto.NewsDTO;

import java.util.Map;

@Mapper
public interface NewsMapper {

    NewsDTO findByTitleAndUrl(String title, String originUrl);

    void insert(NewsDTO dto);

    void imageInsert(Long newsNo, String imageUrl);

    Long getLastInsertId();

    void incrementWatchCount(Long newsNo);


}