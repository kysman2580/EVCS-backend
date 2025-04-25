package com.example.evcs.news.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.evcs.news.model.dto.NewsMainImageDto;
import com.example.evcs.news.model.dto.NewsMainResponseDto;
import com.example.evcs.news.model.service.NaverSearchService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class NaverSearchController {

    private final NaverSearchService naverSearchService;

    public NaverSearchController(NaverSearchService naverSearchService) {
        this.naverSearchService = naverSearchService;
    }

    @GetMapping("/naver-news")  // 🆕 API 엔드포인트 변경
    public ResponseEntity<NewsMainResponseDto> searchNews(@RequestParam("query") String query) {
        try {
            NewsMainResponseDto response = naverSearchService.searchNews(query);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	e.printStackTrace(); // 실제 콘솔 로그로 에러 내용 확인
            return ResponseEntity.internalServerError().body(null);
        }
    }
    
    @GetMapping("/naver-image")  // 🆕 API 엔드포인트 변경
    public ResponseEntity<NewsMainImageDto> searchImage(@RequestParam("query") String query) {
        try {
        	NewsMainImageDto response = naverSearchService.searchImage(query);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	e.printStackTrace(); // 실제 콘솔 로그로 에러 내용 확인
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
