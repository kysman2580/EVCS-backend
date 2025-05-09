package com.example.evcs.news.model.service;

import com.example.evcs.news.model.dao.NewsAdminMapper;
import com.example.evcs.news.model.dto.NewsCategoryDTO;
import com.example.evcs.news.model.dto.NewsDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsAdminServiceImpl implements NewsAdminService {


    private final NewsAdminMapper newsAdminMapper;

    @Override
    public List<NewsCategoryDTO> findAll() {
        return newsAdminMapper.findAll();
    }

    @Override
    public void insert(String newsCategory) {
    	newsAdminMapper.insert(newsCategory);
    }

    @Override
    public void update(String newsCategoryNo, String newsCategory) {
        Map<String, Object> param = new HashMap<>();
        param.put("newsCategoryNo", newsCategoryNo);
        param.put("newsCategory", newsCategory);
        newsAdminMapper.update(param);
    }

    @Override
    public void delete(String newsCategoryNo) {
    	newsAdminMapper.delete(newsCategoryNo);
    }
    
    @Override
    public List<NewsDTO> findAllNews() {
        return newsAdminMapper.findAllNews();
    }
}
