package com.example.evcs.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evcs.news.model.dto.NewsDTO;
import com.example.evcs.news.model.dto.NewsDetailResponse;
import com.example.evcs.news.model.service.NewsDetailServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/news/")
@CrossOrigin(origins = "http://localhost:5173")
public class NewsDetailController {
    
    @Autowired
    private NewsDetailServiceImpl newsDetailService;
    
    @PostMapping("detail")
    public ResponseEntity<?> getNewsDetail(@RequestBody NewsDTO news) {
        System.out.println("detail 요청 왔음 - 제목: " + news.getTitle() + ", URL:" + news.getOriginUrl() + ", imageUrl : " + news.getImageUrl());
        
        NewsDetailResponse newsDetail = newsDetailService.getOrInsertNewsByTitleAndUrl(news);
        
        Map<String, Object> response = new HashMap<>();
        if (newsDetail != null) {
            response.put("exists", true);
            response.put("news", newsDetail.getNews());
            response.put("comments", newsDetail.getComments());
            response.put("likeCount", newsDetail.getLikeCount());
            response.put("hateCount", newsDetail.getHateCount());
            response.put("bookmarked", newsDetail.isBookmarked());
        } else {
            response.put("exists", false);
        }
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("insert")
    public ResponseEntity<?> postNewsDetail(@RequestBody NewsDTO newsDto) {
        System.out.println("post 요청 왔음 - 제목: " + newsDto.getTitle());
        
        NewsDetailResponse newsDetail = newsDetailService.insertAndReturn(newsDto);
        
        Map<String, Object> response = new HashMap<>();
        response.put("news", newsDetail.getNews());
        response.put("comments", newsDetail.getComments());
        response.put("likeCount", newsDetail.getLikeCount());
        response.put("hateCount", newsDetail.getHateCount());
        response.put("bookmarked", newsDetail.isBookmarked());
        
        return ResponseEntity.ok(response);
    }
}