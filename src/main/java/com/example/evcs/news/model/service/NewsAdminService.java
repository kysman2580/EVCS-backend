package com.example.evcs.news.model.service;

import com.example.evcs.news.model.dto.NewsCategoryDTO;
import com.example.evcs.news.model.dto.NewsDTO;

import java.util.List;

public interface NewsAdminService {
    List<NewsCategoryDTO> findAll();
    void insert(String newsCategory);
    void update(String newsCategoryNo, String newsCategory);
    void delete(String newsCategoryNo);
    List<NewsDTO> findAllNews();
}
