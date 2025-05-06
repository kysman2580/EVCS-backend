package com.example.evcs.news.model.dao;

import com.example.evcs.news.model.dto.NewsCategoryDTO;
import com.example.evcs.news.model.dto.NewsDTO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsAdminMapper {
    List<NewsCategoryDTO> findAll();
    void insert(String newsCategory);
    void update(Map<String, Object> param);
    void delete(String newsCategoryNo);
    String findCategoryNoByName(String newsCategory);
    String findCategoryNameByNo(String newsCategoryNo);
    List<NewsDTO> findAllNews();
}